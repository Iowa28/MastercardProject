package ru.aminovniaz.mastercardproject.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.aminovniaz.mastercardproject.dto.CardFilter;
import ru.aminovniaz.mastercardproject.dto.TransactionFilter;
import ru.aminovniaz.mastercardproject.model.Card;
import ru.aminovniaz.mastercardproject.model.CardTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CardDaoImpl implements CardDao {

    @Autowired
    public EntityManager em;

    @Override
    public List<Card> getCards(CardFilter filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Card> cq = cb.createQuery(Card.class);
        Root<Card> root = cq.from(Card.class);

        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.isNotEmpty(filter.getStatus())) {
            predicates.add(cb.equal(root.get("status"), Card.Status.valueOf(filter.getStatus())));
        }
        if (Objects.nonNull(filter.getMinBalance())) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("balance"), filter.getMinBalance()));
        }
        if (Objects.nonNull(filter.getOwnerId())) {
            predicates.add(cb.equal(root.get("owner").get("id"), filter.getOwnerId()));
        }
        predicates.add(cb.isNull(root.get("finishTime")));

        cq.select(root).where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq)
                .setFirstResult((filter.getPage() - 1) * filter.getSize())
                .setMaxResults(filter.getSize())
                .getResultList();
    }

    @Override
    public List<CardTransaction> getCardTransactions(TransactionFilter filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CardTransaction> cq = cb.createQuery(CardTransaction.class);
        Root<CardTransaction> root = cq.from(CardTransaction.class);

        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.isNotEmpty(filter.getOperation())) {
            predicates.add(cb.equal(root.get("operation"), CardTransaction.Operation.valueOf(filter.getOperation())));
        }
        if (Objects.nonNull(filter.getCardId())) {
            predicates.add(cb.equal(root.get("card").get("id"), filter.getCardId()));
        }
        if (Objects.nonNull(filter.getMinAmount())) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("amount"), filter.getMinAmount()));
        }

        cq.select(root).where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq)
                .setFirstResult((filter.getPage() - 1) * filter.getSize())
                .setMaxResults(filter.getSize())
                .getResultList();
    }
}
