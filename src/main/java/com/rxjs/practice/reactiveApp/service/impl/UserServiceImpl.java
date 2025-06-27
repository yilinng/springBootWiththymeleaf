package com.rxjs.practice.reactiveApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rxjs.practice.reactiveApp.model.Role;
import com.rxjs.practice.reactiveApp.model.User;

import com.rxjs.practice.reactiveApp.repository.UserRepository;
import com.rxjs.practice.reactiveApp.service.RoleService;
import com.rxjs.practice.reactiveApp.service.UserService;

import jakarta.annotation.PostConstruct;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleService roleService;
  @Autowired
  private PasswordEncoder bCryptPasswordEncoder;

  // https://stackoverflow.com/questions/62452666/error-creating-bean-in-spring-constructor-threw-exception-nested-exception-is
  @PostConstruct
  public void init() {

    if (!roleService.isExist("Admin")) {
      Role role1 = new Role();
      role1.setName("ADMIN");
      addDefaultRole(role1);
    }

    if (!roleService.isExist("EMPLOYEE")) {
      Role role2 = new Role();
      role2.setName("EMPLOYEE");
      addDefaultRole(role2);
    }

    if (!roleService.isExist("USER")) {
      Role role3 = new Role();
      role3.setName("USER");
      addDefaultRole(role3);
    }

  }

  @Override
  public void save(User user) {
    String passwordEnd = bCryptPasswordEncoder.encode(user.getPassword());
    user.setPassword(passwordEnd);

    // Set default role as USER
    Role role = roleService.findByName("USER");
    Set<Role> roleSet = new HashSet<>();
    roleSet.add(role);

    user.setRoles(roleSet);
    user.setPasswordConfirm(passwordEnd);
    userRepository.save(user);
  }

  @Override
  public User findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  private Role addDefaultRole(Role role) {
    return roleService.AddRole(role);
  }
}
