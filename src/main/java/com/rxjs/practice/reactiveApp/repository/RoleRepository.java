package com.rxjs.practice.reactiveApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rxjs.practice.reactiveApp.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
  Role findRoleByName(String name);

  Boolean existsByName(String name);
}