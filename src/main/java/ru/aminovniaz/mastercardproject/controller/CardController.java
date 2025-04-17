package ru.aminovniaz.mastercardproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.aminovniaz.mastercardproject.dto.CardDto;
import ru.aminovniaz.mastercardproject.dto.CardFilter;
import ru.aminovniaz.mastercardproject.service.CardService;

import java.util.List;

@RestController
@Tag(
        name = "Карты",
        description = "Методы работы с картами"
)
@Validated
public class CardController {

    @Autowired
    private CardService cardService;

    @RequestMapping(value = "admin/card", method = RequestMethod.POST)
    @Operation(summary = "Изменение или создание карты", description = "Изменение данных или создание новой карты")
    public void createOrUpdateCard(@RequestBody @Valid CardDto cardDto) {
        cardService.createOrUpdateCard(cardDto);
    }

    @RequestMapping(value = "admin/card/{id}", method = RequestMethod.DELETE)
    @Operation(summary = "Удаление карты", description = "Удаление карты по идентификатору")
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
    }

    @RequestMapping(value = "admin/card/{id}/block", method = RequestMethod.POST)
    @Operation(summary = "Блокировка карты", description = "Блокировка карты по идентификатору")
    public void blockCard(@PathVariable Long id) {
        cardService.blockCard(id);
    }

    @RequestMapping(value = "admin/card/{id}/activate", method = RequestMethod.POST)
    @Operation(summary = "Активация карты", description = "Активация карты по идентификатору")
    public void activateCard(@PathVariable Long id) {
        cardService.activateCard(id);
    }

    @RequestMapping(value = "admin/cards/all", method = RequestMethod.GET)
    @Operation(summary = "Список всех карт", description = "Получение списка всех карт")
    public List<CardDto> getAllCards(
            @RequestParam @Min(value = 1, message = "Номер страницы начинается с 1") Integer page,
            @RequestParam @Min(value = 0, message = "Количество страниц не может быть меньше 0") Integer size,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Float minBalance
    ) {
        CardFilter filter = CardFilter.builder()
                .page(page)
                .size(size)
                .status(status)
                .minBalance(minBalance)
                .build();

        return cardService.getAllCards(filter);
    }

    @RequestMapping(value = "admin/card/{id}/limit", method = RequestMethod.POST)
    @Operation(summary = "Установка лимита", description = "Установка лимита на совершение операций с картой")
    public void addLimit(@PathVariable Long id, @RequestParam String type, @RequestParam Float limit) {
        cardService.addLimit(id, type, limit);
    }

    @RequestMapping(value = "admin/card/{id}/limit", method = RequestMethod.DELETE)
    @Operation(summary = "Отмена лимита", description = "Отмена лимита на совершение операций с картой")
    public void deleteLimit(@PathVariable Long id, @RequestParam String type) {
        cardService.deleteLimit(id, type);
    }

    @RequestMapping(value = "card/{id}/block", method = RequestMethod.POST)
    @Operation(summary = "Блокировка карты пользователя", description = "Блокировка карты пользователя по идентификатору")
    public void blockUserCard(@PathVariable Long id) {
        cardService.blockUserCard(id);
    }

    @RequestMapping(value = "cards", method = RequestMethod.GET)
    @Operation(summary = "Список карт", description = "Получение списка карт пользователя")
    public List<CardDto> getUserCards(
            @RequestParam @Positive(message = "Номер страницы начинается с 1") Integer page,
            @RequestParam @PositiveOrZero(message = "Количество страниц не может быть меньше 0") Integer size,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Float minBalance
    ) {
        CardFilter filter = CardFilter.builder()
                .page(page)
                .size(size)
                .status(status)
                .minBalance(minBalance)
                .build();

        return cardService.getUserCards(filter);
    }

    @RequestMapping(value = "card/{id}/withdraw", method = RequestMethod.POST)
    @Operation(summary = "Списание средств", description = "Списание средств с карты")
    public void withdrawMoney(@PathVariable Long id, @RequestParam Float amount) {
        cardService.withdrawMoney(id, amount);
    }

    @RequestMapping(value = "cards/transfer", method = RequestMethod.POST)
    @Operation(summary = "Перевод средств", description = "Перевод средств между картами")
    public void transferMoney(@RequestParam Long fromCardId, @RequestParam Long toCardId, @RequestParam Float amount) {
        cardService.transferMoney(fromCardId, toCardId, amount);
    }
}
