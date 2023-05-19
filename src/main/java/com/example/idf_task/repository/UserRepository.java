package com.example.idf_task.repository;

import com.example.idf_task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findBySymbol(String symbol);
}