package ru.aminovniaz.mastercardproject.mapper;

import org.mapstruct.Mapper;
import ru.aminovniaz.mastercardproject.dto.CardDto;
import ru.aminovniaz.mastercardproject.dto.CardTransactionDto;
import ru.aminovniaz.mastercardproject.dto.CardWithTransactionDto;
import ru.aminovniaz.mastercardproject.model.Card;
import ru.aminovniaz.mastercardproject.model.CardTransaction;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {

    CardDto cardToCardDto(Card card);

    List<CardDto> cardsToCardDtos(List<Card> cards);

    List<CardWithTransactionDto> cardsToCardWithTransactionDtos(List<Card> cards);

    List<CardTransactionDto> cardTransactionsToCardTransactionDtos(List<CardTransaction> cardTransactions);
}
