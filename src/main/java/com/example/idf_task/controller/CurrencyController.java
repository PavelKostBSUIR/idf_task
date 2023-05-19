package com.example.idf_task.controller;

import com.example.idf_task.dto.ActualCurrencyInfoDto;
import com.example.idf_task.dto.CurrencyTypeInfoDto;
import com.example.idf_task.service.CurrencyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CurrencyController {
    CurrencyService currencyService;

    @GetMapping("currency")
    public List<CurrencyTypeInfoDto> getCurrencyList() {
        return currencyService.getCurrencies();
    }

    @GetMapping("currency/price")
    public ActualCurrencyInfoDto getCurrencyBySymbol(@RequestParam String symbol) {
        return currencyService.getActualCurrency(symbol);
    }
}
