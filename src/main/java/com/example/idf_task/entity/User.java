package com.example.idf_task.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@Builder
@Getter
public class User {
    @Id
    String username;
    String symbol;
    Double price;
}
