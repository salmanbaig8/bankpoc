package com.bank.poc.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
public class FundTransferResponse {

    private BigDecimal amount;
    private Long transactionReference;
    private String message;

}
