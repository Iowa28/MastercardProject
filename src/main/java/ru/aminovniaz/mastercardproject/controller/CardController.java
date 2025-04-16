package ru.aminovniaz.mastercardproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.aminovniaz.mastercardproject.dto.CardDto;

@RestController
@Tag(
        name = "Карты",
        description = "Методы работы с картами"
)
public class CardController {

    @RequestMapping(value = "example", method = RequestMethod.GET)
    @Operation(summary = "Доступен только авторизованным пользователям")
    public String example() {
        return "Hello, world!";
    }

    @RequestMapping(value = "admin/card", method = RequestMethod.POST)
    @Operation(summary = "Изменение или создание карты", description = "Изменение данных или создание новой карты")
    public void createOrUpdateCard(@RequestBody @Valid CardDto cardDto) {

    }
}
