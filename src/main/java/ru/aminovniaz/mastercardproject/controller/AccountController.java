package ru.aminovniaz.mastercardproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.aminovniaz.mastercardproject.dto.AccountDto;
import ru.aminovniaz.mastercardproject.service.AccountService;

import java.util.List;

@RestController
//@RequestMapping("/admin")
@Tag(
        name = "Аккаунты",
        description = "Методы работы с аккаунтами"
)
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "admin/accounts", method = RequestMethod.GET)
    @Operation(summary = "Список аккаунтов")
    public List<AccountDto> getAccounts() {
        return accountService.getAccounts();
    }

    @RequestMapping(value = "admin/account/{id}", method = RequestMethod.GET)
    @Operation(summary = "Получение аккаунта по идентификатору")
    public AccountDto getAccount(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    @RequestMapping(value = "example", method = RequestMethod.GET)
    @Operation(summary = "Доступен только авторизованным пользователям")
    public String example() {
        return "Hello, world!";
    }
}
