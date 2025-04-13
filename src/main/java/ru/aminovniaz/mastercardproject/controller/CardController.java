package ru.aminovniaz.mastercardproject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.aminovniaz.mastercardproject.dto.CardDto;

@RestController
public class CardController {

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public CardDto test() {
        return CardDto.builder().id(123L).number("123").build();
    }
}
