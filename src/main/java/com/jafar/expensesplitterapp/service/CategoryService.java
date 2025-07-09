package com.jafar.expensesplitterapp.service;

import com.jafar.expensesplitterapp.models.Category;
import com.jafar.expensesplitterapp.models.User;
import com.jafar.expensesplitterapp.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> getUserCategories(User user){
        return categoryRepository.findByCreatedBy(user);
    }
    public Category getByName(String name){
        return categoryRepository.findByName(name).orElseThrow();
    }
}
