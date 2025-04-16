package ru.aminovniaz.mastercardproject.mapper;

import org.mapstruct.Mapper;
import ru.aminovniaz.mastercardproject.dto.AccountDto;
import ru.aminovniaz.mastercardproject.model.Account;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto accountToAccountDto(Account account);

    List<AccountDto> accountsToAccountDtos(List<Account> accounts);
}
