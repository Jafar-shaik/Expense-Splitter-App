package com.jafar.expensesplitterapp.api.response;

import lombok.Data;

import java.util.Map;

@Data
public class CurrencyApi {
    private String base;
    private String date;
    private Map<String , Double> rates;
}
