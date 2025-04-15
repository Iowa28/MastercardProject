package ru.aminovniaz.mastercardproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aminovniaz.mastercardproject.dto.AccountDto;
import ru.aminovniaz.mastercardproject.dto.JwtAuthenticationResponse;
import ru.aminovniaz.mastercardproject.model.Account;

import java.util.Date;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signUp(AccountDto accountDto) {
        Account account = Account.builder()
                .email(accountDto.getEmail())
                .password(passwordEncoder.encode(accountDto.getPassword()))
                .role(Account.Role.USER)
                .createTime(new Date())
                .changeTime(new Date())
                .build();

        accountService.saveAccount(account);

        String jwt = jwtService.generateToken(account);
        return new JwtAuthenticationResponse(jwt);
    }

    @Override
    public JwtAuthenticationResponse signIn(AccountDto accountDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                accountDto.getEmail(),
                accountDto.getPassword()
        ));

        Account account = accountService.getAccountByEmail(accountDto.getEmail());

        String jwt = jwtService.generateToken(account);
        return new JwtAuthenticationResponse(jwt);
    }
}
