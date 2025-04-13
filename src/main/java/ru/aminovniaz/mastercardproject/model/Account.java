package ru.aminovniaz.mastercardproject.model;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "ACCOUNT")
public class Account {
    @Id
    @Column(name = "ACCOUNT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "EMAIL", nullable = false, length = 128)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private Role role;

    @Column(name = "CREATE_TIME", nullable = false)
    private LocalTime createTime;

    @Version
    @Column(name = "CHANGE_TIME", nullable = false)
    private LocalTime changeTime;

    @Column(name = "FINISH_TIME")
    private LocalTime finishTime;

    public enum Role {
        USER, ADMIN
    }
}
