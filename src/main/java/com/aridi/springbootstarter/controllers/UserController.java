package com.aridi.springbootstarter.controllers;

import com.aridi.springbootstarter.entities.UserEntity;
import com.aridi.springbootstarter.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/users-create-form")
    public String createFormUser(UserEntity userEntity){
        return "user-create";
    }

    @PostMapping("/users")
    public String createUser(UserEntity userEntity) {
        userService.createOrUpdate(userEntity);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<UserEntity> allUsers = userService.findAll();
        model.addAttribute("allUsers", allUsers);
        return "all-users";
    }

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable("id") Integer id,
                              Model model) {
        UserEntity user = userService.findById(id);
        model.addAttribute(user);
        return "all-users";
    }

    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable("id") UserEntity userEntity, Model model) {
        userService.createOrUpdate(userEntity);
        return "all-users";
    }


    public UserController(UserService userService) {
        this.userService = userService;
    }
}
