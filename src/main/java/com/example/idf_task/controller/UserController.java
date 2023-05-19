package com.example.idf_task.controller;

import com.example.idf_task.dto.CreateUserDto;
import com.example.idf_task.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping("notify")
    public void register(@RequestBody CreateUserDto createUserDto) {
        userService.register(createUserDto);
    }
}
