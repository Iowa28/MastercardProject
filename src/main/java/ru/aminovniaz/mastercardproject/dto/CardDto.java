package ru.aminovniaz.mastercardproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@Schema(description = "Карта")
public class CardDto {

    @Schema(description = "Идентификатор")
    private Long id;

    @Schema(description = "Номер")
    @Size(min = 16, max = 16, message = "Номер карты должен содержать 16 символов")
    @NotBlank(message = "Номер карты не может быть пустым")
    private String number;

    @Schema(description = "Владелец")
    @NotNull(message = "Владелец карты не может быть пустым")
    private CardOwner owner;

    @Schema(description = "Срок действия в днях")
    private Integer activeDays;

    @Schema(description = "Статус")
    private String status;

    @Schema(description = "Баланс")
    private Float balance;

    @Schema(description = "Дата создания")
    private Date createTime;

    @Schema(description = "Дата изменения")
    private Date changeTime;

    @Schema(description = "Дата удаления")
    private Date finishTime;
}
