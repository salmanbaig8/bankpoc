package com.bank.poc.repository;

import com.bank.poc.exception.AccountNotFoundException;
import com.bank.poc.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(String accountNumber);
}
