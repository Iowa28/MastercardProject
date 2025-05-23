package ru.aminovniaz.mastercardproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aminovniaz.mastercardproject.model.Account;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> getAllByFinishTimeIsNull();

    Optional<Account> findByEmail(String email);

    boolean existsByEmail(String email);
}
