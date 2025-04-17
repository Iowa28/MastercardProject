package ru.aminovniaz.mastercardproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aminovniaz.mastercardproject.dao.CardDao;
import ru.aminovniaz.mastercardproject.dto.CardDto;
import ru.aminovniaz.mastercardproject.dto.CardFilter;
import ru.aminovniaz.mastercardproject.exception.EntityExistsException;
import ru.aminovniaz.mastercardproject.exception.NotFoundException;
import ru.aminovniaz.mastercardproject.mapper.CardMapper;
import ru.aminovniaz.mastercardproject.model.Account;
import ru.aminovniaz.mastercardproject.model.Card;
import ru.aminovniaz.mastercardproject.model.CardLimit;
import ru.aminovniaz.mastercardproject.repository.CardLimitRepository;
import ru.aminovniaz.mastercardproject.repository.CardRepository;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardMapper cardMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CardDao cardDao;

    @Autowired
    private CardLimitRepository cardLimitRepository;

    @Override
    public void createOrUpdateCard(CardDto cardDto) {
        String encodedNumber = passwordEncoder.encode(cardDto.getNumber());
        Card card;
        if (Objects.isNull(cardDto.getId())) {
            card = new Card();
            card.setStatus(Card.Status.ACTIVE);
            card.setActiveDays(30);
            card.setBalance(5000f);
            card.setCreateTime(new Date());
        }
        else {
            card = getCardById(cardDto.getId());
        }

        card.setNumber(encodedNumber);
        Account account = accountService.getAccountById(cardDto.getOwner().getId());
        card.setOwner(account);
        if (Objects.nonNull(cardDto.getActiveDays())) {
            card.setActiveDays(cardDto.getActiveDays());
        }
        if (Objects.nonNull(cardDto.getStatus())) {
            card.setStatus(Card.Status.valueOf(cardDto.getStatus()));
        }
        if (Objects.nonNull(cardDto.getBalance())) {
            card.setBalance(cardDto.getBalance());
        }
        cardRepository.save(card);
    }

    @Override
    public void deleteCard(Long cardId) {
        Card card = getCardById(cardId);
        card.setStatus(Card.Status.BLOCKED);
        card.setFinishTime(new Date());
        cardRepository.save(card);
    }

    @Override
    public void blockCard(Long cardId) {
        Card card = getCardById(cardId);
        card.setStatus(Card.Status.BLOCKED);
        cardRepository.save(card);
    }

    @Override
    public void activateCard(Long cardId) {
        Card card = getCardById(cardId);
        card.setStatus(Card.Status.ACTIVE);
        cardRepository.save(card);
    }

    @Override
    public List<CardDto> getAllCards(CardFilter filter) {
        List<Card> cards = cardDao.getCards(filter);
        return cardMapper.cardsToCardDtos(cards);
    }

    @Override
    public void addLimit(Long cardId, String type, Float limit) {
        CardLimit.Type limitType = CardLimit.Type.valueOf(type);
        if (cardLimitRepository.existsByCardIdAndTypeAndFinishTimeIsNull(cardId, limitType)) {
            throw new EntityExistsException("На карту уже установлен лимит с данным типом.");
        }

        CardLimit cardLimit = new CardLimit();
        Card card = getCardById(cardId);
        cardLimit.setCard(card);
        cardLimit.setType(limitType);
        cardLimit.setWithdrawLimit(limit);
        cardLimit.setRemnant(limit);
        cardLimit.setCreateTime(new Date());
        cardLimitRepository.save(cardLimit);
    }

    @Override
    public void deleteLimit(Long cardId, String type) {
        CardLimit cardLimit = cardLimitRepository.findByCardIdAndTypeAndFinishTimeIsNull(cardId, CardLimit.Type.valueOf(type))
                .orElseThrow(() -> new NotFoundException("У карты отсутсвует лимит с данным типом."));
        cardLimit.setFinishTime(new Date());
        cardLimitRepository.save(cardLimit);
    }

    @Override
    public void blockUserCard(Long cardId) {
        Account account = accountService.getCurentAccount();

        Card card = getCardById(cardId);
        if (!card.getOwner().getId().equals(account.getId())) {
            throw new AccessDeniedException("Вы не являетесь владельцем карты.");
        }

        card.setStatus(Card.Status.BLOCKED);
        cardRepository.save(card);
    }

    @Override
    public List<CardDto> getUserCards(CardFilter filter) {
        Account account = accountService.getCurentAccount();
        filter.setOwnerId(account.getId());

        List<Card> cards = cardDao.getCards(filter);
        return cardMapper.cardsToCardDtos(cards);
    }

    @Transactional
    @Override
    public void withdrawMoney(Long cardId, Float amount) {
        Account account = accountService.getCurentAccount();

        Card card = getCardById(cardId);
        if (!card.getOwner().getId().equals(account.getId())) {
            throw new AccessDeniedException("Вы не являетесь владельцем карты.");
        }

        if (card.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Недостаточно средств для списания.");
        }

        if (card.getStatus() != Card.Status.ACTIVE) {
            throw new IllegalStateException("Необходимо активировать карту.");
        }

        List<CardLimit> limits = cardLimitRepository.findByCardIdAndFinishTimeIsNull(cardId);
        for (CardLimit limit : limits) {
            if (limit.getRemnant().compareTo(amount) < 0) {
                throw new IllegalStateException("Превышен лимит списания средств.");
            }
            limit.setRemnant(limit.getRemnant() - amount);
            cardLimitRepository.save(limit);
        }

        card.setBalance(card.getBalance() - amount);
        cardRepository.save(card);
    }

    @Transactional
    @Override
    public void transferMoney(Long fromCardId, Long toCardId, Float amount) {
        Account account = accountService.getCurentAccount();

        Card fromCard = getCardById(fromCardId);
        if (!fromCard.getOwner().getId().equals(account.getId())) {
            throw new AccessDeniedException(String.format("Вы не являетесь владельцем карты %s.", fromCardId));
        }

        Card toCard = getCardById(toCardId);
        if (!toCard.getOwner().getId().equals(account.getId())) {
            throw new AccessDeniedException(String.format("Вы не являетесь владельцем карты %s.", toCardId));
        }

        if (fromCard.getStatus() != Card.Status.ACTIVE) {
            throw new IllegalStateException(String.format("Необходимо активировать карту %s.", fromCardId));
        }
        if (toCard.getStatus() != Card.Status.ACTIVE) {
            throw new IllegalStateException(String.format("Необходимо активировать карту %s.", toCardId));
        }

        if (fromCard.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Недостаточно средств для перевода.");
        }

        List<CardLimit> limits = cardLimitRepository.findByCardIdAndFinishTimeIsNull(fromCardId);
        for (CardLimit limit : limits) {
            if (limit.getRemnant().compareTo(amount) < 0) {
                throw new IllegalStateException("Превышен лимит перевода средств.");
            }
            limit.setRemnant(limit.getRemnant() - amount);
            cardLimitRepository.save(limit);
        }

        fromCard.setBalance(fromCard.getBalance() - amount);
        cardRepository.save(fromCard);
        toCard.setBalance(toCard.getBalance() + amount);
        cardRepository.save(toCard);
    }

    private Card getCardById(Long cardId) {
        return cardRepository.findById(cardId)
                .orElseThrow(() -> new NotFoundException("Карта c данным идентификатором не найдена."));
    }
}
