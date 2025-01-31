package com.eazybytes.accounts.service.client;

import com.eazybytes.accounts.dto.LoanResponseDto;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "loans", path = "/api/v1/loans")
public interface LoanFeignClient {
    @GetMapping("/all")
    public ResponseEntity<List<LoanResponseDto>> getAllLoanDetails(@RequestParam("mobileNumber") String mobileNumber);
}
