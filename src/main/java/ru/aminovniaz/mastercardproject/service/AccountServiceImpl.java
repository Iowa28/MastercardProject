package ru.aminovniaz.mastercardproject.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.aminovniaz.mastercardproject.dto.AccountDto;
import ru.aminovniaz.mastercardproject.mapper.AccountMapper;
import ru.aminovniaz.mastercardproject.exception.EntityExistsException;
import ru.aminovniaz.mastercardproject.exception.NotFoundException;
import ru.aminovniaz.mastercardproject.model.Account;
import ru.aminovniaz.mastercardproject.model.AccountDetails;
import ru.aminovniaz.mastercardproject.repository.AccountRepository;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<AccountDto> getAccounts() {
        List<Account> accounts = accountRepository.getAllByFinishTimeIsNull();
        return accountMapper.accountsToAccountDtos(accounts);
    }

    @Override
    public AccountDto getAccount(long accountId) {
        return accountMapper.accountToAccountDto(getAccountById(accountId));
    }

    @Override
    public Account getAccountByEmail(String email) {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Аккаунт c данной почтой не найден."));
    }

    @Override
    public Account getAccountById(long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Аккаунт c данным идентификатором не найден."));
    }

    @Override
    public void saveAccount(Account account) {
        if (accountRepository.existsByEmail(account.getEmail())) {
            throw new EntityExistsException("Аккаунт c данной почтой уже существует.");
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
    public void createOrUpdateAccount(AccountDto accountDto) {
        Account account;
        if (Objects.isNull(accountDto.getId())) {
            if (accountRepository.existsByEmail(accountDto.getEmail())) {
                throw new EntityExistsException("Аккаунт c данной почтой уже существует.");
            }

            account = new Account();
            account.setRole(Account.Role.USER);
            account.setCreateTime(new Date());
        }
        else {
            account = getAccountById(accountDto.getId());
            if (!StringUtils.equals(account.getEmail(), accountDto.getEmail()) && accountRepository.existsByEmail(accountDto.getEmail())) {
                throw new EntityExistsException("Аккаунт c данной почтой уже существует.");
            }
        }

        account.setEmail(accountDto.getEmail());
        account.setPassword(accountDto.getEncodedPassword());
        if (StringUtils.isNotEmpty(accountDto.getRole())) {
            account.setRole(Account.Role.valueOf(accountDto.getRole()));
        }
        accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = getAccountById(id);
        account.setFinishTime(new Date());
        accountRepository.save(account);
    }
}
