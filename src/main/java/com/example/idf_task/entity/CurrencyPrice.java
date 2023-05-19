package com.example.idf_task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@Builder
@Getter
public class CurrencyPrice {
    @Id
    String symbol;
    Double price;
}
