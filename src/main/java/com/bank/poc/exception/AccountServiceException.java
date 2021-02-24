package com.bank.poc.exception;

public class AccountServiceException extends RuntimeException{

    public AccountServiceException(String message){
        super(message);
    }
}
