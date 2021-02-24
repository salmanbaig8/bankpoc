package com.bank.poc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class AccountStatement {

    private Double currentBalance;
    List<Transaction> transactions;

}
