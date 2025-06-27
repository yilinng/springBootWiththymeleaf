package com.rxjs.practice.reactiveApp.service;

import com.rxjs.practice.reactiveApp.model.Role;

public interface RoleService {
  Role findByName(String name);

  Role AddRole(Role role);

  Boolean isExist(String name);
}
