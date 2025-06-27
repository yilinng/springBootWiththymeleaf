package com.rxjs.practice.reactiveApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rxjs.practice.reactiveApp.model.User;

public interface UserRepository extends MongoRepository<User, String> {
  User findByUsername(String username);

  Boolean existsByUsername(String username);
}