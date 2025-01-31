package com.eazybytes.accounts.dto;

import lombok.Data;

@Data
public class LoanResponseDto {
    private long loanId;
    private String customerName;
    private String mobileNumber;
    private String loanType;
    private int loanAmount;
    private int loanAmountPaid;
    private int outstandingLoanAmount;
}
