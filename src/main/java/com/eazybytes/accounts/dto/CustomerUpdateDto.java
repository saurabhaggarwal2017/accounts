package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(
        name = "UpdateDetails",
        description = "Schema to hold Account and customer details for update."
)
@Data
public class CustomerUpdateDto {
    @Schema(
            description = "Name of the customer", example = "Eazy Bytes"
    )
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;
    @Schema(
            description = "Email address of the customer", example = "tutor@eazybytes.com"
    )
    @Email(message = "Email address should be a valid value")
    private String email;
    @Schema(
            description = "Account type of Bank account", example = "Savings"
    )
    @NotEmpty(message = "AccountType can not be a null or empty") // saving, current, salary
    private String accountType;
    @Schema(
            description = "Bank branch address", example = "123 NewYork"
    )
    @NotEmpty(message = "BranchAddress can not be a null or empty")
    private String branchAddress;
}
