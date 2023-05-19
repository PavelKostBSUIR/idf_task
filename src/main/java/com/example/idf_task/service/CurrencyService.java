package com.example.idf_task.service;

import com.example.idf_task.domain.ConfigurationData;
import com.example.idf_task.domain.CurrencyType;
import com.example.idf_task.dto.ActualCurrencyInfoDto;
import com.example.idf_task.dto.LoadActualCurrencyDto;
import com.example.idf_task.dto.CurrencyTypeInfoDto;
import com.example.idf_task.entity.CurrencyPrice;
import com.example.idf_task.entity.User;
import com.example.idf_task.repository.CurrencyPriceRepository;
import com.example.idf_task.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CurrencyService {
    ConfigurationData configurationData;
    CurrencyPriceRepository currencyPriceRepository;
    UserRepository userRepository;

    private LoadActualCurrencyDto loadActualCurrency(CurrencyType currencyType) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<LoadActualCurrencyDto[]> response = restTemplate.getForEntity(
                "https://api.coinlore.net/api/ticker/?id=" + currencyType.getId(),
                LoadActualCurrencyDto[].class);
        return Arrays.stream(Objects.requireNonNull(response.getBody())).toList().get(0);
    }

    public ActualCurrencyInfoDto getActualCurrency(String symbol) {
        return new ActualCurrencyInfoDto(currencyPriceRepository.findBySymbol(symbol).orElseThrow(RuntimeException::new).getPrice());
    }

    public List<CurrencyTypeInfoDto> getCurrencies() {
        return configurationData.getCurrencyTypes().stream().map(currencyType -> new CurrencyTypeInfoDto(currencyType.getSymbol())).toList();
    }

    private void saveActualCurrency(LoadActualCurrencyDto loadActualCurrencyDto, CurrencyType currencyType) {

        currencyPriceRepository.save(CurrencyPrice.builder().price(loadActualCurrencyDto.getPrice()).symbol(currencyType.getSymbol()).build());
    }

    private void checkUserCurrency(CurrencyType currencyType) {
        List<User> users = userRepository.findBySymbol(currencyType.getSymbol());
        CurrencyPrice currencyPrice = currencyPriceRepository.findBySymbol(currencyType.getSymbol()).orElseThrow(RuntimeException::new);
        users.forEach(user -> {
            Double userPrice = user.getPrice();
            Double actualPrice = currencyPrice.getPrice();
            Double percent = Math.abs((actualPrice - userPrice) / userPrice * 100.0);
            if (percent > 1.0) {
                log.warn("Symbol:{},Username:{},Percent:{}.", currencyType.getSymbol(), user.getUsername(), percent);
            }
        });
    }


    @Scheduled(fixedDelay = 1000 * 60, initialDelay = 0)
    public void updateCurrencyTask() {
        configurationData.getCurrencyTypes().forEach(currencyType -> {
            LoadActualCurrencyDto loadActualCurrencyDto = loadActualCurrency(currencyType);
            saveActualCurrency(loadActualCurrencyDto, currencyType);
            checkUserCurrency(currencyType);
        });
    }
}
