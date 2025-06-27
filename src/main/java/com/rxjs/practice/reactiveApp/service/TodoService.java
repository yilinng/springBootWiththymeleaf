package com.rxjs.practice.reactiveApp.service;

import java.util.List;

import com.rxjs.practice.reactiveApp.model.Todo;

public interface TodoService {
  List<Todo> getAllTodo();

  void save(Todo todo);

  Todo getById(String id);

  void deleteViaId(String id);
}
