package ru.aminovniaz.mastercardproject.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ru.aminovniaz.mastercardproject.model.Account;
import ru.aminovniaz.mastercardproject.security.AccountDetails;

import java.util.List;

@TestConfiguration
public class SpringSecurityWebAuxTestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        Account account = Account.builder()
                .id(1L)
                .email("test@mail.com")
                .password("password")
                .role(Account.Role.ADMIN)
                .build();
        AccountDetails accountDetails = new AccountDetails(account);

        return new InMemoryUserDetailsManager(List.of(accountDetails));
    }
}
