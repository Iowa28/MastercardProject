package ru.aminovniaz.mastercardproject.service;

import ru.aminovniaz.mastercardproject.dto.AccountDto;

import java.util.List;

public interface AccountService {

    List<AccountDto> getAccounts();

    AccountDto getAccount(long accountId);
}
