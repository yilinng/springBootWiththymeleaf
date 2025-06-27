package com.rxjs.practice.reactiveApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.rxjs.practice.reactiveApp.model.Todo;

public interface TodoRepository extends MongoRepository<Todo, String> {
  @Query("{_id: '?0'}")
  Todo findByTodoId(String id);

}
