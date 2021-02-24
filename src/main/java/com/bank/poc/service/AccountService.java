package com.bank.poc.service;

import com.bank.poc.model.Transaction;
import com.bank.poc.model.request.FundTransferRequest;
import com.bank.poc.model.response.FundTransferResponse;

import java.util.List;

public interface AccountService {

    FundTransferResponse fundTransfer(FundTransferRequest fundTransferRequest);

    List<Transaction> generateStatement(String accountNumber, Integer year, Integer month);
}
