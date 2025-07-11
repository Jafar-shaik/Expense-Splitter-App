package com.jafar.expensesplitterapp.controllers;

import com.jafar.expensesplitterapp.models.User;
import com.jafar.expensesplitterapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUsers(){
        List<User> users= userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
