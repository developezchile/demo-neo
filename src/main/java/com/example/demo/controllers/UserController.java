package com.example.demo.controllers;


import com.example.demo.controllers.exceptions.DataValidationException;
import com.example.demo.controllers.exceptions.UserNotFoundException;
import com.example.demo.dtos.UserDTO;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Iterable<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            return userService.findById(uuid);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody User user, @RequestHeader HttpHeaders headers) {
        try {
            return userService.create(user, headers.get("Authorization").get(0));
        } catch (Exception e) {
            throw new DataValidationException();
        }
    }


}
