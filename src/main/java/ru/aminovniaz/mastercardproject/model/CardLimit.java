package ru.aminovniaz.mastercardproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "CARD_LIMIT")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardLimit {

    @Id
    @Column(name = "CARD_LIMIT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARD_ID", nullable = false)
    private Card card;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private Type type;

    @Column(name = "WITHDRAW_LIMIT")
    private Float withdrawLimit;

    @Column(name = "REMNANT")
    private Float remnant;

    @Column(name = "CREATE_TIME", nullable = false)
    private Date createTime;

    @Version
    @Column(name = "CHANGE_TIME", nullable = false)
    private Date changeTime;

    @Column(name = "FINISH_TIME")
    private Date finishTime;

    public enum Type {
        DAY, MONTH
    }
}
