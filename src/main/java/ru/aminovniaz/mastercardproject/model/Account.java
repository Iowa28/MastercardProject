package ru.aminovniaz.mastercardproject.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "ACCOUNT")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @Column(name = "ACCOUNT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EMAIL", nullable = false, length = 36)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private Role role;

    @Column(name = "CREATE_TIME", nullable = false)
    private Date createTime;

    @Version
    @Column(name = "CHANGE_TIME", nullable = false)
    private Date changeTime;

    @Column(name = "FINISH_TIME")
    private Date finishTime;

    public enum Role {
        USER, ADMIN
    }
}
