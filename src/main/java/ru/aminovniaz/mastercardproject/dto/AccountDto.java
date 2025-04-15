package ru.aminovniaz.mastercardproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "Аккаунт")
public class AccountDto {

    @Schema(description = "Идентификатор")
    private Long id;

    @Schema(description = "Почта")
    @Size(min = 5, max = 36, message = "Адрес электронной почты должен содержать от 5 до 36 символов")
    @NotBlank(message = "Адрес электронной почты не может быть пустым")
    @Email(message = "Адрес электронной должен быть в формате user@example.com")
    private String email;

    @Size(max = 64, message = "Длина пароля должна быть не более 64 символов")
    private String password;

    private String encodedPassword;

    @Schema(description = "Роль аккаунта")
    private String role;

    @Schema(description = "Дата создания")
    private Date createTime;

    @Schema(description = "Дата изменения")
    private Date changeTime;

    @Schema(description = "Дата удаления")
    private Date finishTime;
}
