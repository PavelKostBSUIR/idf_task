package com.example.idf_task.repository;

import com.example.idf_task.entity.CurrencyPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyPriceRepository extends JpaRepository<CurrencyPrice, String> {
    Optional<CurrencyPrice> findBySymbol(String symbol);
}