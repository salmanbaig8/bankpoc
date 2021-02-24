package com.bank.poc.service.impl;

import com.bank.poc.CommonUtil;
import com.bank.poc.exception.AccountNotFoundException;
import com.bank.poc.exception.AccountServiceException;
import com.bank.poc.model.Account;
import com.bank.poc.model.Transaction;
import com.bank.poc.model.TransactionType;
import com.bank.poc.model.request.FundTransferRequest;
import com.bank.poc.model.response.FundTransferResponse;
import com.bank.poc.repository.AccountRepository;
import com.bank.poc.repository.TransactionRepository;
import com.bank.poc.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.*;
import java.util.List;
import java.util.Objects;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    public final String SUCCESS_TRANSACTION = "Transaction Successful";
    public final String CREDIT = "cr";
    public final String DEBIT = "dr";
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public FundTransferResponse fundTransfer(FundTransferRequest fundTransferRequest) {

        BigDecimal remainingBalance = BigDecimal.valueOf(0);
        Long transactionRef = Long.valueOf(0);
        try {
            String fromAccountNumber = fundTransferRequest.getFromAccountNumber();
            String toAccountNumber = fundTransferRequest.getToAccountNumber();

            Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber);
            Account toAccount = accountRepository.findByAccountNumber(toAccountNumber);
            if(Objects.isNull(fromAccount) || Objects.isNull(toAccount)){
             throw new AccountNotFoundException("Invalid Account Number");
            }
            BigDecimal amount = BigDecimal.valueOf(fundTransferRequest.getAmount());
            remainingBalance=fromAccount.getCurrentBalance().subtract(amount);
            if(remainingBalance.intValue() < 0){
                throw new AccountServiceException("Insufficient Balance");
            }
            fromAccount.setCurrentBalance(remainingBalance);
            accountRepository.save(fromAccount);
            toAccount.setCurrentBalance(toAccount.getCurrentBalance().add(amount));
            accountRepository.save(toAccount);

            Transaction fromTransaction = transactionRepository.save(new Transaction(0L,fromAccountNumber, LocalDateTime.now(), amount, DEBIT));
            transactionRef = fromTransaction.getTransactionId();
            Transaction toTransaction = transactionRepository.save(new Transaction(0L,toAccountNumber, LocalDateTime.now(), amount, CREDIT));


        }
        catch(Exception e){
            log.error(e.getMessage());
            throw new AccountServiceException(e.getMessage());
        }

        return new FundTransferResponse(remainingBalance, transactionRef, SUCCESS_TRANSACTION);

    }

    @Override
    public List<Transaction> generateStatement(String accountNumber, Integer year, Integer month) {

        Year inputYear = Year.of(year);
        Year currentYear = Year.now();
        CommonUtil.validateYear(inputYear,currentYear);
        CommonUtil.validateMonth(month);
        LocalDateTime startDateTime = null;
        LocalDateTime enDateTime = null;
        try {
            YearMonth yearMonth = YearMonth.of(year, month);
            LocalDate startDate = yearMonth.atDay(1);
            LocalDate endDate = yearMonth.atEndOfMonth();
            startDateTime = LocalDateTime.of(startDate, LocalTime.MIN);
            enDateTime = LocalDateTime.of(endDate, LocalTime.MAX);
        }
        catch(Exception e){
            log.error(e.getMessage());
            throw new AccountServiceException(e.getMessage());
        }
        return transactionRepository.findByTransactionTimeBetweenAndAccountNumber(startDateTime, enDateTime, accountNumber);

    }
}
