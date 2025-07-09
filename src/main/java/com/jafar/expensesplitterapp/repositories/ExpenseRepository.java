package com.jafar.expensesplitterapp.repositories;

import com.jafar.expensesplitterapp.models.Expense;
import com.jafar.expensesplitterapp.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExpenseRepository extends MongoRepository<Expense,String > {
    List<Expense> findByCreatedBy(User user);
}
