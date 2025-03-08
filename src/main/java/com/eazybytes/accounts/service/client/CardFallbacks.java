package com.eazybytes.accounts.service.client;

import com.eazybytes.accounts.dto.CardDto;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardFallbacks implements CardFeignClient{
    @Override
    public ResponseEntity<List<CardDto>> getAllCardsDetails(String correlationId, String mobileNumber) {
        return null;
    }
}
