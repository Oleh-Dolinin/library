package com.example.library.controller;

import com.example.library.model.Role;
import com.example.library.model.User;
import com.example.library.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class NewUser {

    private final UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public NewUser(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody User user, @AuthenticationPrincipal User author) {
        System.out.println(author);
        if (userRepo.findByUsername(user.getUsername()) != null || user.getUsername().equals("") || user.getPassword().equals("")){
            return new ResponseEntity<>("Pardon, this nick name are exist", HttpStatus.OK);
        }
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Collections.singleton(Role.USER));
            user.setActive(true);
            userRepo.save(user);
            return new ResponseEntity<>("Saved", HttpStatus.OK);
        }
    }
}