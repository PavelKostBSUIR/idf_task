package com.example.idf_task.configuration;

import com.example.idf_task.domain.ConfigurationData;
import com.example.idf_task.domain.CurrencyType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLOutput;
import java.util.*;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BeanConfiguration {
    @Value("${currency}")
    String currency;

    @Bean
    public ConfigurationData configurationData() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<CurrencyType> currencyTypes = objectMapper.readValue(currency.substring(1), new TypeReference<>() {
        });
        return new ConfigurationData(Collections.unmodifiableList(currencyTypes));
    }
}
