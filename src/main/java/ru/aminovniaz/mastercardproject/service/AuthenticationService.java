package ru.aminovniaz.mastercardproject.service;

import ru.aminovniaz.mastercardproject.dto.AccountDto;
import ru.aminovniaz.mastercardproject.security.JwtAuthenticationResponse;

public interface AuthenticationService {

    JwtAuthenticationResponse signUp(AccountDto request);

    JwtAuthenticationResponse signIn(AccountDto request);
}
