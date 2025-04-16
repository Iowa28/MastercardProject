package ru.aminovniaz.mastercardproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Владелец карты")
public class CardOwner {

    @Schema(description = "Идентификатор")
    @NotNull(message = "Идентификатор владельца карты не может быть пустым")
    private Long id;
}
