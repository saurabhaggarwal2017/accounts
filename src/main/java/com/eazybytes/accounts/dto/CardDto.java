package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CardDto {

    private String cardOwnerName;

    private String mobileNumber;

    private long cardNumber;

    private String cardType;

    private String validDate;

    private int cvv;

    private int cardLimit;

    private int amountUsed;

    private int avilableBalance;
}

