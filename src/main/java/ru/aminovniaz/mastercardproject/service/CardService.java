package ru.aminovniaz.mastercardproject.service;

import ru.aminovniaz.mastercardproject.dto.CardDto;

public interface CardService {

    void createOrUpdateCard(CardDto cardDto);

    void deleteCard(Long cardId);

    void blockCard(Long cardId);

    void activateCard(Long cardId);
}
