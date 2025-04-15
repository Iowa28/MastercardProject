package ru.aminovniaz.mastercardproject.dto;

import org.mapstruct.Mapper;
import ru.aminovniaz.mastercardproject.model.Account;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto accountToAccountDto(Account account);

    List<AccountDto> accountsToAccountDtos(List<Account> accounts);

    Account accountDtoToAccount(AccountDto accountDto);
}
