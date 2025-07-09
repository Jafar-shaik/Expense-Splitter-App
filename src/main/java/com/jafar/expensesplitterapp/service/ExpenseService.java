package com.jafar.expensesplitterapp.service;

import com.jafar.expensesplitterapp.models.Category;
import com.jafar.expensesplitterapp.models.Expense;
import com.jafar.expensesplitterapp.models.User;
import com.jafar.expensesplitterapp.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Transactional
    public Expense addExpense(Expense expense){
        expense.setCreatedAt(LocalDateTime.now());
        return expenseRepository.save(expense);
    }

    public List<Expense> getUserExpences(User user){
        return expenseRepository.findByCreatedBy(user);
    }
}
