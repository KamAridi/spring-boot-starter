package com.aridi.springbootstarter.controllers;

import com.aridi.springbootstarter.dto.UserCreateEditDto;
import com.aridi.springbootstarter.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

//    @GetMapping("/users-create-form")
//    public String createFormUser(UserEntity userEntity){
//        return "user-create";
//    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("allUsers", userService.findAll());
        return "all-users";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model) {
        return userService.findById(id)
                .map(userReadDto -> {
                    model.addAttribute("user", userReadDto);
                    return "redirect:/users";
                })
                .orElseThrow((() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@ModelAttribute UserCreateEditDto userCreateEditDto) {
        userService.create(userCreateEditDto);
        return "redirect:/users";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Integer id, UserCreateEditDto userCreateEditDto, Model model) {
        return userService.update(id, userCreateEditDto)
                .map(userReadDto -> {
                    model.addAttribute(userReadDto);
                    return "redirect:/users";
                })
                .orElseThrow((() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        if (!userService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/users";
    }
}
