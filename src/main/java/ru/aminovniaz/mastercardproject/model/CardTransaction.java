package ru.aminovniaz.mastercardproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "CARD_TRANSACTION")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardTransaction {

    @Id
    @Column(name = "CARD_TRANSACTION_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARD_ID", nullable = false)
    private Card card;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "OPERATION", nullable = false)
    private Operation operation;

    @Column(name = "AMOUNT")
    private Float amount;

    @Column(name = "CREATE_TIME", nullable = false)
    private Date createTime;

    public enum Operation {
        WITHDRAW, REPLENISH, TRANSFER
    }
}
