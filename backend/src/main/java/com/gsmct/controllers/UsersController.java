package com.gsmct.controllers;

import com.gsmct.entities.Admin;
import com.gsmct.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private AdminRepository userRepository;

    @GetMapping("/users")
    public List<Admin> getUsers() {
        return (List<Admin>) userRepository.findAll();
    }

    @PostMapping("/users")
    void addUser(@RequestBody Admin user) {
        userRepository.save(user);
    }
}
