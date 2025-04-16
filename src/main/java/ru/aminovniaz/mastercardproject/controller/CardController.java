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
    public void deleteCard(@PathVariable long id) {
        cardService.deleteCard(id);
    }

    @RequestMapping(value = "admin/card/{id}/block", method = RequestMethod.POST)
    @Operation(summary = "Блокировка карты", description = "Блокировка карты по идентификатору")
    public void blockCard(@PathVariable long id) {
        cardService.blockCard(id);
    }

    @RequestMapping(value = "admin/card/{id}/activate", method = RequestMethod.POST)
    @Operation(summary = "Активация карты", description = "Активация карты по идентификатору")
    public void activateCard(@PathVariable long id) {
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

    @RequestMapping(value = "card/{id}/block", method = RequestMethod.POST)
    @Operation(summary = "Блокировка карты пользователя", description = "Блокировка карты пользователя по идентификатору")
    public void blockUserCard(@PathVariable long id) {
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
}
