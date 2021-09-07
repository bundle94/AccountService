package com.blueharvest.accountservice.repository;

import com.blueharvest.accountservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findTopByOrderByIdDesc();
}
