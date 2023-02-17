package com.app.controllers;

import com.app.repo.QuoteRepository;
import com.app.repo.User;
import com.app.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping("/adduser")
    public String addUser(@Valid User user) {
        boolean userExist = !userRepository
                .findById(user.getName())
                .orElseGet(() -> userRepository.save(user)).equals(user);
        if (userExist) return "User already exists";
        else return "User added successfully";

    }
}
