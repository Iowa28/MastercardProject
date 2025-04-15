package ru.aminovniaz.mastercardproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.aminovniaz.mastercardproject.dto.AccountDto;
import ru.aminovniaz.mastercardproject.dto.AccountMapper;
import ru.aminovniaz.mastercardproject.exception.EntityExistsException;
import ru.aminovniaz.mastercardproject.exception.NotFoundException;
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
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Аккаунт c данным идентификатором не найден."));
    }

    @Override
    public Account getAccountByEmail(String email) {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Аккаунт c данной почтой не найден."));
    }

    @Override
    public void saveAccount(Account account) {
        if (accountRepository.existsByEmail(account.getEmail())) {
            throw new EntityExistsException("Пользователь с таким email уже существует");
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
