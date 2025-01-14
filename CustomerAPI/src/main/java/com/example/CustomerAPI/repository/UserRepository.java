package com.example.CustomerAPI.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.CustomerAPI.entity.User;


public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByOrderByScoreDesc();
}
