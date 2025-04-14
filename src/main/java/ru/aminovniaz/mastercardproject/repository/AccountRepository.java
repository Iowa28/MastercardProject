package ru.aminovniaz.mastercardproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aminovniaz.mastercardproject.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
