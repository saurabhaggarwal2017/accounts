package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.dto.*;
import com.eazybytes.accounts.entity.Account;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.ICustomerService;
import com.eazybytes.accounts.service.client.CardFeignClient;
import com.eazybytes.accounts.service.client.LoanFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;
    private CardFeignClient cardFeignClient;
    private LoanFeignClient loanFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Account account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        AccountDto accountDto = AccountMapper.mapToAccountDto(account, new AccountDto());
        customerDetailsDto.setAccount(accountDto);
        ResponseEntity<List<CardDto>> cards = cardFeignClient.getAllCardsDetails(correlationId, mobileNumber);
        ResponseEntity<List<LoanResponseDto>> loans = loanFeignClient.getAllLoanDetails(correlationId, mobileNumber);
        if (cards != null) {
            customerDetailsDto.setCards(cards.getBody());
        }
        if (loans != null) {
            customerDetailsDto.setLoanResponse(loans.getBody());
        }
        return customerDetailsDto;

    }
}
