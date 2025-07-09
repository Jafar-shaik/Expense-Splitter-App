package com.jafar.expensesplitterapp.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
@NoArgsConstructor
@Data
public class Category {

    @Id
    private String id;
    @NonNull
    @Indexed(unique = true)
    private String name;
    @DBRef
    private User createdBy;
}
