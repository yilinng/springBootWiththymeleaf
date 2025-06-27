package com.rxjs.practice.reactiveApp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "role")
public class Role {
  @Id
  private String id;

  private String name;
}
