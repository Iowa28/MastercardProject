package ru.aminovniaz.mastercardproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aminovniaz.mastercardproject.model.CardLimit;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardLimitRepository extends JpaRepository<CardLimit, Long> {

    boolean existsByCardIdAndTypeAndFinishTimeIsNull(Long cardId, CardLimit.Type type);

    Optional<CardLimit> findByCardIdAndTypeAndFinishTimeIsNull(Long cardId, CardLimit.Type type);

    List<CardLimit> findByCardIdAndFinishTimeIsNull(Long cardId);
}
