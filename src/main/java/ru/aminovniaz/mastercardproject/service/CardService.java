package ru.aminovniaz.mastercardproject.service;

import ru.aminovniaz.mastercardproject.dto.CardDto;
import ru.aminovniaz.mastercardproject.dto.CardFilter;

import java.util.List;

public interface CardService {

    void createOrUpdateCard(CardDto cardDto);

    void deleteCard(Long cardId);

    void blockCard(Long cardId);

    void activateCard(Long cardId);

    List<CardDto> getAllCards(CardFilter filter);

    void blockUserCard(Long cardId);

    List<CardDto> getUserCards(CardFilter filter);
}
