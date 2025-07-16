package com.jafar.expensesplitterapp.api.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JokeApi {

    private String type;
    private String setup;
    private String punchline;
}
