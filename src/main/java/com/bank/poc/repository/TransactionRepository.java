package com.bank.poc.repository;

import com.bank.poc.model.Account;
import com.bank.poc.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByTransactionTimeBetweenAndAccountNumber(LocalDateTime startDate, LocalDateTime endDate, String accountNumber);

}
