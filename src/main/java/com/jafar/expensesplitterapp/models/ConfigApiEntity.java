package com.jafar.expensesplitterapp.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "expense_api_config")
public class ConfigApiEntity {
    private String key;
    private String value;
}
