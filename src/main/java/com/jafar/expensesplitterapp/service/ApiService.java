package com.jafar.expensesplitterapp.service;

import com.jafar.expensesplitterapp.api.response.CurrencyApi;
import com.jafar.expensesplitterapp.api.response.JokeApi;
import com.jafar.expensesplitterapp.api.response.QuoteApi;
import com.jafar.expensesplitterapp.config.AppCache;
import com.jafar.expensesplitterapp.constants.PlaceHolders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;



@Service
public class ApiService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AppCache appCache;

    @Value("${api.ninjas.key}")
    private String apiKey;

    public Double convertCurrency(String from , String to, double amount){
//       https://api.frankfurter.app/latest?amount=<amount>&from=<from>&to=<to>   --> this is stored in mongoDB as value
//       String  url= "https://api.frankfurter.app/latest?amount="+amount+"&from="+from+"&to="+to;  --> the below line meaning
        String url=appCache.appCache.get("currencyApi").replace(PlaceHolders.FROM,from).replace(PlaceHolders.TO,to).replace(PlaceHolders.AMOUNT,String.valueOf(amount));
        CurrencyApi response=restTemplate.getForObject(url, CurrencyApi.class);
        return response.getRates().get(to);
    }

    public JokeApi getRandomJoke(){
        String url=appCache.appCache.get("jokeApi");
        return restTemplate.getForObject(url, JokeApi.class);
    }

    public QuoteApi getRandomLoveQuote(){
//        https://api.api-ninjas.com/v1/quotes   --> this is stored in mongoDB as value
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("X-Api-Key",apiKey);
        HttpEntity<Void> entity=new HttpEntity<>(httpHeaders);
        String url=appCache.appCache.get("quoteApi");
        ResponseEntity<QuoteApi[]> response=restTemplate.exchange(url, HttpMethod.GET,entity, QuoteApi[].class);
        QuoteApi[] quotes=response.getBody();
        if (quotes == null || quotes.length==0){
            throw  new RuntimeException("Quote is not generated ");
        }
        return quotes[0];
    }
}
