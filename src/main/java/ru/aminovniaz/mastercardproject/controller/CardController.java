package ru.aminovniaz.mastercardproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.aminovniaz.mastercardproject.dto.CardDto;
import ru.aminovniaz.mastercardproject.service.CardService;

@RestController
@Tag(
        name = "Карты",
        description = "Методы работы с картами"
)
public class CardController {

    @Autowired
    private CardService cardService;

    @RequestMapping(value = "example", method = RequestMethod.GET)
    @Operation(summary = "Доступен только авторизованным пользователям")
    public String example() {
        return "Hello, world!";
    }

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
}
