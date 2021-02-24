package com.bank.poc.model.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Setter
@Getter
public class FundTransferRequest {

    @Length(min = 10, max = 10, message = "The Account number is invalid")
    private String fromAccountNumber;
    @Length(min = 10, max = 10, message = "The Account number is invalid")
    private String toAccountNumber;
    @Min(100)
    @Max(100000)
    private double amount;
}
