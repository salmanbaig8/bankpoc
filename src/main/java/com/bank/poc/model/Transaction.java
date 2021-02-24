package com.bank.poc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "TRANSACTION")
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;
    @Column(name = "TRANSACTION_TIME")
    private LocalDateTime transactionTime;
    private BigDecimal amount;
    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;

}
