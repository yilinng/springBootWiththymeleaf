package com.rxjs.practice.reactiveApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rxjs.practice.reactiveApp.model.Todo;

import com.rxjs.practice.reactiveApp.service.impl.TodoServiceImpl;

@Controller
public class TodoController {
  @Autowired
  private TodoServiceImpl todoServiceImpl;

  @GetMapping("/")
  public String viewHomePage(Model model) {
    model.addAttribute("allTodolist", todoServiceImpl.getAllTodo());
    return "index";
  }

  // @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('EMPLOYEE')")
  // @PreAuthorize("hasAuthority('USER')")
  @GetMapping("/todos/add")
  public String addNewTodo(Model model) {
    Todo todo = new Todo();
    model.addAttribute("todo", todo);
    return "newTodo";
  }

  @PostMapping("/save")
  public String save(@ModelAttribute("todo") Todo todo) {

    todoServiceImpl.save(todo);
    return "redirect:/";
  }

  @GetMapping("/todos/update/{id}")
  public String updateForm(@PathVariable(value = "id") String id, Model model) {
    Todo todo = todoServiceImpl.getById(id);
    model.addAttribute("todo", todo);
    return "updateTodo";
  }

  @GetMapping("/todos/{id}")
  public String getThroughId(@PathVariable(value = "id") String id, Model model) {
    Todo todo = todoServiceImpl.getById(id);
    model.addAttribute("todo", todo);
    return "todosDetails";

  }

  @PostMapping("/todos/delete/{id}")
  public String deleteThroughId(@PathVariable(value = "id") String id) {
    todoServiceImpl.deleteViaId(id);
    return "redirect:/";

  }
}
