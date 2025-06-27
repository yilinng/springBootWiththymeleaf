package com.rxjs.practice.reactiveApp.service;

import com.rxjs.practice.reactiveApp.model.User;

public interface UserService {
  void save(User user);

  User findByUsername(String username);
}
