package ru.aminovniaz.mastercardproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aminovniaz.mastercardproject.model.CardTransaction;

@Repository
public interface CardTransactionRepository extends JpaRepository<CardTransaction, Long> {
}
