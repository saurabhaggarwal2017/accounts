package com.eazybytes.accounts.service.client;

import com.eazybytes.accounts.dto.CardDto;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "cards", path = "/api/v1/cards", fallback = CardFallbacks.class)
public interface CardFeignClient {

//    @Retry(name = "cardRetry")
    @GetMapping(value = "/all", consumes = "application/json")
    public ResponseEntity<List<CardDto>> getAllCardsDetails(
            @RequestHeader("eazybank-correlation-id") String correlationId,
            @RequestParam("mobileNumber") String mobileNumber);
}
