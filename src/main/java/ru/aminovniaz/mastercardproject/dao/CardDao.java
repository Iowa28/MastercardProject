package ru.aminovniaz.mastercardproject.dao;

import ru.aminovniaz.mastercardproject.dto.CardFilter;
import ru.aminovniaz.mastercardproject.dto.TransactionFilter;
import ru.aminovniaz.mastercardproject.model.Card;
import ru.aminovniaz.mastercardproject.model.CardTransaction;

import java.util.List;

public interface CardDao {

    List<Card> getCards(CardFilter filter);

    List<CardTransaction> getCardTransactions(TransactionFilter filter);
}
