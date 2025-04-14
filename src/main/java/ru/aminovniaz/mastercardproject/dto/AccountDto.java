package ru.aminovniaz.mastercardproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "Аккаунт")
public class AccountDto {

    @Schema(description = "Идентификатор")
    private Long id;

    @Schema(description = "Почта")
    private String email;

    private String password;

    @Schema(description = "Роль аккаунта")
    private String role;

    @Schema(description = "Дата создания")
    private Date createTime;

    @Schema(description = "Дата изменения")
    private Date changeTime;

    @Schema(description = "Дата удаления")
    private Date finishTime;
}
