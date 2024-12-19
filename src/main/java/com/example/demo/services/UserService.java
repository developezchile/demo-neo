package com.example.demo.services;

import com.example.demo.dtos.UserDTO;
import com.example.demo.entities.User;

import java.util.UUID;

public interface UserService {

    Iterable<User> findAll();

    User findById(UUID id);

    UserDTO create(User user, String token);



}
