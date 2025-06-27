package com.rxjs.practice.reactiveApp.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rxjs.practice.reactiveApp.model.Todo;
import com.rxjs.practice.reactiveApp.repository.TodoRepository;
import com.rxjs.practice.reactiveApp.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {

  @Autowired
  private TodoRepository todoRepository;

  @Override
  public List<Todo> getAllTodo() {
    return todoRepository.findAll();
  }

  @Override
  public void save(Todo todo) {
    LocalDateTime updateDate = LocalDateTime.now();

    if (todo.getCreateDate() != null) {
      todo.setUpdateDate(updateDate);
    } else {
      todo.setCreateDate(updateDate);
    }

    todoRepository.save(todo);
  }

  @Override
  public Todo getById(String id) {

    Optional<Todo> optional = todoRepository.findById(id);
    Todo todo = null;
    if (optional.isPresent())
      todo = optional.get();
    else
      throw new RuntimeException(
          "Todo not found for id : " + id);
    return todo;
  }

  @Override
  public void deleteViaId(String id) {
    todoRepository.deleteById(id);
  }

}
