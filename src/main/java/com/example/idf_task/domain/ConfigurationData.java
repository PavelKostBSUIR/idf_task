package com.example.idf_task.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ConfigurationData {
    List<CurrencyType> currencyTypes;
}
