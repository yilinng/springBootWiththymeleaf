package com.rxjs.practice.reactiveApp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "user")
public class User {
  @Id
  private String id;

  @Indexed(unique = true)
  private String username;

  private String password;

  private String passwordConfirm;

  private Set<Role> roles;

  @DBRef
  private List<Todo> todos = new ArrayList<>();

}
