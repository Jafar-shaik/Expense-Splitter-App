package com.jafar.expensesplitterapp.models;


import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class User {
    @Id
    private String id;

    @NonNull
    @Indexed(unique = true)
    private String userName;

    private String email;

    @NonNull
    private String password;

    private List<String > roles= new ArrayList<>();
}
