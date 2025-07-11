package com.jafar.expensesplitterapp.service;

import com.jafar.expensesplitterapp.models.CurrencyResponse;
import com.jafar.expensesplitterapp.models.JokeAPI;
import com.jafar.expensesplitterapp.models.LoveQuote;
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

    @Value("${api.ninjas.key}")
    private String apiKey;
    @Value("${api.ninjas.url}")
    private String apiUrl;

    public Double convertCurrency(String from , String to, double amount){
        String  url= "https://api.frankfurter.app/latest?amount="+amount+"&from="+from+"&to="+to;
        CurrencyResponse response=restTemplate.getForObject(url, CurrencyResponse.class);
        return response.getRates().get(to);
    }

    public JokeAPI getRandomJoke(){
        String url="https://official-joke-api.appspot.com/random_joke";
        return restTemplate.getForObject(url, JokeAPI.class);
    }

    // category=love is a premium-only feature on the API Ninjas service. So i removed the commented line in the below method
    public LoveQuote getRandomLoveQuote(){
//        String url = apiUrl + "?category=love";
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("X-Api-Key",apiKey);
        HttpEntity<Void> entity=new HttpEntity<>(httpHeaders);
        ResponseEntity<LoveQuote[]> response=restTemplate.exchange(apiUrl, HttpMethod.GET,entity,LoveQuote[].class);
        LoveQuote[] quotes=response.getBody();
        if (quotes == null || quotes.length==0){
            throw  new RuntimeException("Quote is not generated ");
        }
        return quotes[0];
    }
}
