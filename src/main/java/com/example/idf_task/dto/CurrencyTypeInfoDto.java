package com.example.idf_task.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.awt.*;
import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CurrencyTypeInfoDto {
    String symbol;
}
