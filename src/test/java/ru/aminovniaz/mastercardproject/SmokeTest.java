package ru.aminovniaz.mastercardproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.aminovniaz.mastercardproject.controller.AccountController;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
class SmokeTest {

    @Autowired
    private AccountController accountController;

    @Test
    void contextLoads() {
        assertThat(accountController, is(notNullValue()));
    }
}
