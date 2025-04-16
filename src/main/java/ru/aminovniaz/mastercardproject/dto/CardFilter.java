package ru.aminovniaz.mastercardproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Фильтр карты")
public class CardFilter {

    @Schema(description = "Страница")
    private Integer page;

    @Schema(description = "Количество")
    private Integer size;

    @Schema(description = "Статус")
    private String status;

    @Schema(description = "Минимальный баланс")
    private Float minBalance;

    @Schema(hidden = true)
    private Long ownerId;
}
