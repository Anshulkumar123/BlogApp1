package com.blogapp1.controller;

import com.blogapp1.entity.User;
import com.blogapp1.payload.Signup;
import com.blogapp1.reposiotry.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    //http://localhost:8080/api/auth/sign-up
    @PostMapping("/sign-up")
    public ResponseEntity<String> createUser(@RequestBody Signup signup){
        if(userRepository.existsByEmail(signup.getEmail())){
            return new ResponseEntity<>("email id is already registered", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(userRepository.existsByUsername(signup.getUsername())){
            return new ResponseEntity<>("user name is already registered", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        User user = new User();
        user.setName(signup.getName());
        user.setUsername(signup.getUsername());
        user.setEmail(signup.getEmail());
        user.setPassword(signup.getPassword());

        userRepository.save(user);

        return new ResponseEntity<>("user registered", HttpStatus.CREATED);

    }
}
