package com.bank.poc.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data @EqualsAndHashCode(exclude = {"customers"})
@Entity
@Table(name = "ACCOUNT")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "accountNumber")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Long accountId;
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;
    @Column(name = "AMOUNT")
    private BigDecimal currentBalance;
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "ACCOUNT_CUSTOMER",
            joinColumns = { @JoinColumn(name = "ACCOUNT_ID") },
            inverseJoinColumns = { @JoinColumn(name = "CUSTOMER_ID") })
    private Set<Customer> customers = new HashSet<>();

}
