package com.rxjs.practice.reactiveApp.service;

public interface SecurityService {
  boolean isAuthenticated();

  void autoLogin(String username, String password);

}
