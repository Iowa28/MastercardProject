package ru.aminovniaz.mastercardproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "Транзакция карты")
public class CardTransactionDto {

    @Schema(description = "Идентификатор")
    private Long id;

//    @Schema(description = "Карта")
//    private CardDto card;

    @Schema(description = "Операция")
    private String operation;

    @Schema(description = "Сумма")
    private Float amount;

    @Schema(description = "Дата создания")
    private Date createTime;
}
