package com.jafar.expensesplitterapp.repositories;

import com.jafar.expensesplitterapp.models.Category;
import com.jafar.expensesplitterapp.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category,String > {
    List<Category> findByCreatedBy(User user);
    Optional<Category> findByName(String name);
}
