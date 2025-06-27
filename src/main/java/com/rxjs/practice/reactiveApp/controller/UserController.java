package com.rxjs.practice.reactiveApp.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rxjs.practice.reactiveApp.model.User;
import com.rxjs.practice.reactiveApp.service.SecurityService;
import com.rxjs.practice.reactiveApp.service.UserService;
import com.rxjs.practice.reactiveApp.web.UserValidator;

@Controller
public class UserController {
  @Autowired
  private UserService userService;

  @Autowired
  private SecurityService securityService;

  @Autowired
  private UserValidator userValidator;

  @GetMapping("/registration")
  public String registration(Model model) {
    if (securityService.isAuthenticated()) {
      return "redirect:/";
    }

    model.addAttribute("userForm", new User());

    return "registration";
  }

  @PostMapping("/registration")
  public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {

    System.out.println("userForm registration" + userForm);
    System.out.println("bindingResult registration" + userForm);

    System.out.println("init bindingResult.hasErrors" + bindingResult.hasErrors());
    userValidator.validate(userForm, bindingResult);

    System.out.println("bindingResult registration" + bindingResult);

    System.out.println("after bindingResult.hasErrors" + bindingResult.hasErrors());

    if (bindingResult.hasErrors()) {
      System.out.println(" bindingResult hasErrors !!");
      return "registration";
    } else {
      System.out.println(" bindingResult has no Errors");
      userService.save(userForm);

      securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

      return "redirect:/";
    }

  }

  @GetMapping("/login")
  public String login(Model model, String error, String logout) {
    if (securityService.isAuthenticated()) {
      return "redirect:/";
    }

    if (error != null)
      model.addAttribute("error", "Your username and password is invalid.");

    if (logout != null)
      model.addAttribute("message", "You have been logged out successfully.");

    return "login";
  }

  /*
   * @GetMapping({ "/", "/welcome" })
   * public String welcome(Model model) {
   * return "welcome";
   * }
   */
}
