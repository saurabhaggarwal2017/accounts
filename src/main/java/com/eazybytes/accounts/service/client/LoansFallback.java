package com.eazybytes.accounts.service.client;

import com.eazybytes.accounts.dto.LoanResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoansFallback implements LoanFeignClient{
    @Override
    public ResponseEntity<List<LoanResponseDto>> getAllLoanDetails(String correlationId, String mobileNumber) {
        return null;
    }
}
