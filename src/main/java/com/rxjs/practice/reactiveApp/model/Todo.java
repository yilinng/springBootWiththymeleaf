package com.rxjs.practice.reactiveApp.model;

import lombok.Data;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "todo")
public class Todo {
  @Id
  private String id;

  private String title;

  private String content;

  private LocalDateTime createDate;

  private LocalDateTime updateDate;

  private String userId;
}
