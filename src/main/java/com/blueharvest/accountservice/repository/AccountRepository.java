package com.blueharvest.accountservice.repository;

import com.blueharvest.accountservice.model.Account;
import com.blueharvest.accountservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}