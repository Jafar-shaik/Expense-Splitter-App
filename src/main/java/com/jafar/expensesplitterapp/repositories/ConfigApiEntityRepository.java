package com.jafar.expensesplitterapp.repositories;

import com.jafar.expensesplitterapp.models.ConfigApiEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigApiEntityRepository extends MongoRepository<ConfigApiEntity,String > {
}
