package com.jafar.expensesplitterapp.controllers;


import com.jafar.expensesplitterapp.models.Category;
import com.jafar.expensesplitterapp.models.User;
import com.jafar.expensesplitterapp.service.CategoryService;
import com.jafar.expensesplitterapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody Category category, @AuthenticationPrincipal UserDetails userDetails){
        User foundUser=userService.getByUserName(userDetails.getUsername()).orElseThrow();
        category.setCreatedBy(foundUser);
        Category savedCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(savedCategory);
    }

    @GetMapping
    public ResponseEntity<?> getUserCategories(@AuthenticationPrincipal UserDetails userDetails){
        User foundUser=userService.getByUserName(userDetails.getUsername()).orElseThrow();
        List<Category> categories=categoryService.getUserCategories(foundUser);
        return ResponseEntity.ok(categories);
    }
}
