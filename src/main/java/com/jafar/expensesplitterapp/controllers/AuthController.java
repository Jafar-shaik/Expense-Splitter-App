package com.jafar.expensesplitterapp.controllers;

import com.jafar.expensesplitterapp.models.User;
import com.jafar.expensesplitterapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody User user){
        User savedUser=userService.register(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> getUserByName(@PathVariable String userName){
        User foundUser= userService.getByUserName(userName).orElseThrow(()-> new RuntimeException("User not found with name "+userName));
        return ResponseEntity.ok(foundUser);
    }
}
