package com.jafar.expensesplitterapp.service;

import com.jafar.expensesplitterapp.models.User;
import com.jafar.expensesplitterapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(User user){
        if(userRepository.existsByUserName(user.getUserName())){
            throw  new RuntimeException("User is already exist by that name");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("ROLE_USER"));
        return userRepository.save(user);
    }

    public Optional<User> getByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
