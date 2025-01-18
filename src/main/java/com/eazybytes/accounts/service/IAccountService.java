package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.CustomerUpdateDto;

public interface IAccountService {
    void createAccount(CustomerDto customerDto);

    CustomerDto getAccount(String mobileNumber);

    void updateAccount(String mobileNumber, CustomerUpdateDto customerDto);

    void deleteAccount(String mobileNumber);
}
