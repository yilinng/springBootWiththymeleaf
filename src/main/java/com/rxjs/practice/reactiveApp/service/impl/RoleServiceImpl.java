package com.rxjs.practice.reactiveApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rxjs.practice.reactiveApp.model.Role;
import com.rxjs.practice.reactiveApp.repository.RoleRepository;
import com.rxjs.practice.reactiveApp.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public Role findByName(String name) {
    // Find role by name using the roleDao
    Role role = roleRepository.findRoleByName(name);
    return role;
  }

  @Override
  public Role AddRole(Role role) {
    return roleRepository.save(role);
  }

  @Override
  public Boolean isExist(String name) {
    return roleRepository.existsByName(name);
  }
  /*
   * @Override
   * public void initDb() {
   * roleRepository.deleteAll();
   * }
   */
}
