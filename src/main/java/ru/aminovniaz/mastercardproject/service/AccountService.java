package ru.aminovniaz.mastercardproject.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.aminovniaz.mastercardproject.dto.AccountDto;
import ru.aminovniaz.mastercardproject.model.Account;
import ru.aminovniaz.mastercardproject.model.AccountDetails;

import java.util.List;

public interface AccountService {

    List<AccountDto> getAccounts();

    AccountDto getAccount(long accountId);

    Account getAccountByEmail(String email);

    Account getAccountById(long accountId);

    void saveAccount(Account accountDto);

    AccountDetails getAccountDetails(String email);

    UserDetailsService userDetailsService();

    void createOrUpdateAccount(AccountDto accountDto);

    void deleteAccount(Long id);
}
