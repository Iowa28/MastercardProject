package ru.aminovniaz.mastercardproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    @RequestMapping(value = "example", method = RequestMethod.GET)
    @Operation(summary = "Доступен только авторизованным пользователям")
    public String example() {
        return "Hello, world!";
    }
}
