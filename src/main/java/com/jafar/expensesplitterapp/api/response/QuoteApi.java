package com.jafar.expensesplitterapp.api.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuoteApi {
    private String quote;
    private String author;
    private String category;
}
