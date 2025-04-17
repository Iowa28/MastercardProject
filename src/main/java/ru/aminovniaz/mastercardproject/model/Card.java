package ru.aminovniaz.mastercardproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CARD")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @Column(name = "CARD_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NUMBER", nullable = false, length = 64)
    private String number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account owner;

    @Column(name = "ACTIVE_DAYS")
    private Integer activeDays;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private Status status;

    @Column(name = "BALANCE")
    private Float balance;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "card")
    @Cascade(CascadeType.ALL)
    private List<CardTransaction> transactions = new ArrayList<>();

    @Column(name = "CREATE_TIME", nullable = false)
    private Date createTime;

    @Version
    @Column(name = "CHANGE_TIME", nullable = false)
    private Date changeTime;

    @Column(name = "FINISH_TIME")
    private Date finishTime;

    public enum Status {
        ACTIVE, BLOCKED, EXPIRED
    }
}