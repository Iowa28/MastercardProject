package ru.aminovniaz.mastercardproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.aminovniaz.mastercardproject.dto.AccountDto;
import ru.aminovniaz.mastercardproject.security.JwtAuthenticationResponse;
import ru.aminovniaz.mastercardproject.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@Tag(
        name = "Аутентификация",
        description = "Методы работы с аутентификацией"
)
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @RequestMapping(value = "sign-up", method = RequestMethod.POST)
    public JwtAuthenticationResponse signUp(@RequestBody @Valid AccountDto accountDto) {
        return authenticationService.signUp(accountDto);
    }

    @Operation(summary = "Авторизация пользователя")
    @RequestMapping(value = "sign-in", method = RequestMethod.POST)
    public JwtAuthenticationResponse signIn(@RequestBody @Valid AccountDto accountDto) {
        return authenticationService.signIn(accountDto);
    }
}
