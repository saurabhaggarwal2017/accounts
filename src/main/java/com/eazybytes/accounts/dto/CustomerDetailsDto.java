package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDetailsDto {
    private String name;
    private String mobileNumber;
    private String email;
    private AccountDto account;
    private List<LoanResponseDto> loanResponse;
    private List<CardDto> cards;
}
