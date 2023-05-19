package com.example.idf_task.service;

import com.example.idf_task.dto.CreateUserDto;
import com.example.idf_task.entity.CurrencyPrice;
import com.example.idf_task.entity.User;
import com.example.idf_task.repository.CurrencyPriceRepository;
import com.example.idf_task.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    CurrencyPriceRepository currencyPriceRepository;

    public void register(CreateUserDto createUserDto) {
        CurrencyPrice currencyPrice = currencyPriceRepository.findBySymbol(createUserDto.getSymbol()).orElseThrow(RuntimeException::new);
        userRepository.save(User.builder().username(createUserDto.getUsername()).price(currencyPrice.getPrice()).symbol(createUserDto.getSymbol()).build());
    }
}
