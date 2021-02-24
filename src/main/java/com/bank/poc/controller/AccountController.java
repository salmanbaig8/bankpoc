package com.bank.poc.controller;

import com.bank.poc.model.Transaction;
import com.bank.poc.model.request.FundTransferRequest;
import com.bank.poc.model.response.FundTransferResponse;
import com.bank.poc.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/funds")
    public ResponseEntity<FundTransferResponse> fundTransfer(@RequestBody FundTransferRequest fundTransferRequest){
       return new ResponseEntity<>(accountService.fundTransfer(fundTransferRequest), HttpStatus.ACCEPTED);
    }

    @GetMapping("/statements")
    public ResponseEntity<List<Transaction>> generateStatement(@RequestParam String accountNumber, @RequestParam Integer year,
                                                              @RequestParam Integer month){
        List<Transaction> getStatements = accountService.generateStatement(accountNumber, year, month);
        if(CollectionUtils.isEmpty(getStatements)){
            return new ResponseEntity("No Statement available for the current search", HttpStatus.OK);
        }
        else return new ResponseEntity<>(getStatements, HttpStatus.OK);
    }
}
