package ru.aminovniaz.mastercardproject.service;

import ru.aminovniaz.mastercardproject.dto.*;

import java.util.List;

public interface CardService {

    void createOrUpdateCard(CardDto cardDto);

    void deleteCard(Long cardId);

    void blockCard(Long cardId);

    void activateCard(Long cardId);

    List<CardWithTransactionDto> getAllCards(CardFilter filter);

    void addLimit(Long cardId, String type, Float limit);

    void deleteLimit(Long cardId, String type);

    void blockUserCard(Long cardId);

    List<CardDto> getUserCards(CardFilter filter);

    void withdrawMoney(Long cardId, Float amount);

    void transferMoney(Long fromCardId, Long toCardId, Float amount);

    void addMoney(Long cardId, Float amount);

    List<CardTransactionDto> getCardTransactions(TransactionFilter filter);
}