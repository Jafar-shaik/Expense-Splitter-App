package com.jafar.expensesplitterapp.controllers;

import com.jafar.expensesplitterapp.models.Category;
import com.jafar.expensesplitterapp.models.Expense;
import com.jafar.expensesplitterapp.models.User;
import com.jafar.expensesplitterapp.service.CategoryService;
import com.jafar.expensesplitterapp.service.ExpenseService;
import com.jafar.expensesplitterapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createExpense(@RequestBody Expense expense, @AuthenticationPrincipal UserDetails userDetails){
        User foundUser=userService.getByUserName(userDetails.getUsername()).orElseThrow();
        Category category=categoryService.getByName(expense.getCategory().getName());
        expense.setCategory(category);
        expense.setCreatedBy(foundUser);
        Expense savedExpense = expenseService.addExpense(expense);
        return ResponseEntity.ok(savedExpense);
    }

    @GetMapping
    public ResponseEntity<?> getUserExpense(@AuthenticationPrincipal UserDetails userDetails){
        User foundUser=userService.getByUserName(userDetails.getUsername()).orElseThrow();
        List<Expense> expenseList=expenseService.getUserExpences(foundUser);
        return ResponseEntity.ok(expenseList);
    }
}
