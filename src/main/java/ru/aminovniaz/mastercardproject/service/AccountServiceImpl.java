package ru.aminovniaz.mastercardproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aminovniaz.mastercardproject.dto.AccountDto;
import ru.aminovniaz.mastercardproject.dto.AccountMapper;
import ru.aminovniaz.mastercardproject.model.Account;
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
        Account account = accountRepository.findById(accountId).orElseThrow();
        return accountMapper.accountToAccountDto(account);
    }
}
