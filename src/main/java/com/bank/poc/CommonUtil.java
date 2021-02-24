package com.bank.poc;

import com.bank.poc.exception.AccountServiceException;

import java.time.LocalDate;
import java.time.Year;

public class CommonUtil {

    private CommonUtil(){

    }

    public static void validateYear(Year inputYear, Year currentYear){
        if(inputYear.isAfter(currentYear)){
            throw new AccountServiceException("Year is greater than current year");
        }
    }

    public static void validateMonth(Integer month){
        if ( month > LocalDate.now().getMonthValue() ){
            throw new AccountServiceException("Input Month is greater than current Month");
        }
    }
}
