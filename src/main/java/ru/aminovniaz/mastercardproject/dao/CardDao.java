package ru.aminovniaz.mastercardproject.dao;

import ru.aminovniaz.mastercardproject.dto.CardFilter;
import ru.aminovniaz.mastercardproject.model.Card;

import java.util.List;

public interface CardDao {

    List<Card> getCards(CardFilter filter);
}
