package ru.aminovniaz.mastercardproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.aminovniaz.mastercardproject.dto.AccountDto;
import ru.aminovniaz.mastercardproject.dto.AccountMapper;
import ru.aminovniaz.mastercardproject.model.Account;
import ru.aminovniaz.mastercardproject.model.AccountDetails;
import ru.aminovniaz.mastercardproject.repository.AccountRepository;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<AccountDto> getAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accountMapper.accountsToAccountDtos(accounts);
    }

    @Override
    public AccountDto getAccount(long accountId) {
        return accountMapper.accountToAccountDto(getAccountById(accountId));
    }

    @Override
    public Account getAccountById(long accountId) {
        // TODO: Заменить на свои исключения
        return accountRepository.findById(accountId).orElseThrow();
    }

    @Override
    public Account getAccountByEmail(String email) {
        // TODO: Заменить на свои исключения
        return accountRepository.findByEmail(email).orElseThrow();
    }

    @Override
    public void saveAccount(Account account) {
        if (accountRepository.existsByEmail(account.getEmail())) {
            // TODO: Заменить на свои исключения
            throw new IllegalStateException("Пользователь с таким email уже существует");
        }

        accountRepository.save(account);
    }

    @Override
    public AccountDetails getAccountDetails(String email) {
        Account account = getAccountByEmail(email);
        return new AccountDetails(account);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return this::getAccountDetails;
    }

    @Override
    public AccountDetails getCurrentAccount() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return getAccountDetails(email);
    }
}
