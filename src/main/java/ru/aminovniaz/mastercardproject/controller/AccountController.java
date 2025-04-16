package ru.aminovniaz.mastercardproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.aminovniaz.mastercardproject.dto.AccountDto;
import ru.aminovniaz.mastercardproject.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Tag(
        name = "Аккаунты",
        description = "Методы работы с аккаунтами"
)
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "accounts", method = RequestMethod.GET)
    @Operation(summary = "Список аккаунтов", description = "Получение списка аккаунтов")
    public List<AccountDto> getAccounts() {
        return accountService.getAccounts();
    }

    @RequestMapping(value = "account/{id}", method = RequestMethod.GET)
    @Operation(summary = "Получение аккаунта", description = "Получение аккаунта по идентификатору")
    public AccountDto getAccount(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    @RequestMapping(value = "account", method = RequestMethod.POST)
    @Operation(summary = "Изменение или создание аккаунта", description = "Изменение данных или создание нового аккаунта")
    public void createOrUpdateAccount(@RequestBody @Valid AccountDto accountDto) {
        accountDto.setEncodedPassword(passwordEncoder.encode(accountDto.getPassword()));
        accountService.createOrUpdateAccount(accountDto);
    }

    @RequestMapping(value = "account/{id}", method = RequestMethod.DELETE)
    @Operation(summary = "Удаление аккаунта", description = "Удаление аккаунта по идентификатору")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }
}
