package com.example.CustomerAPI.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    private String userId;
    private String username;
    private long score;
    private Set<String> badges = new HashSet<>();
}
