package ru.aminovniaz.mastercardproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Фильтр транзакций")
public class TransactionFilter {

    @Schema(description = "Страница")
    private Integer page;

    @Schema(description = "Количество")
    private Integer size;

    @Schema(description = "Карта")
    private Long cardId;

    @Schema(description = "Операция")
    private String operation;

    @Schema(description = "Минимальная сумма")
    private Float minAmount;
}
