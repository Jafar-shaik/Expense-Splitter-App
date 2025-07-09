package com.jafar.expensesplitterapp.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "expenses")
@Data
@NoArgsConstructor
public class Expense {

    @Id
    private String id;
    @NonNull
    @Indexed(unique = true)
    private String title;

    private double amount;

    @DBRef
    private Category category;
    @DBRef
    private User createdBy;
    private LocalDateTime createdAt;
}
