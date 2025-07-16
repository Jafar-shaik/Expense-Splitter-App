package com.jafar.expensesplitterapp.controllers;

import com.jafar.expensesplitterapp.api.response.JokeApi;
import com.jafar.expensesplitterapp.api.response.QuoteApi;
import com.jafar.expensesplitterapp.models.User;
import com.jafar.expensesplitterapp.service.ApiService;
import com.jafar.expensesplitterapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ApiController {
    @Autowired
    private ApiService apiService;
    @Autowired
    private UserService userService;

    @GetMapping("currency")
    public ResponseEntity<?> convert(@RequestParam String from,
                                     @RequestParam String to,
                                     @RequestParam double amount,
                                     @AuthenticationPrincipal UserDetails userDetails){
        User user=userService.getByUserName(userDetails.getUsername()).orElseThrow(()-> new RuntimeException("User not found with name "+userDetails.getUsername()));
        double result= apiService.convertCurrency(from, to, amount);
        return ResponseEntity.ok("hey "+user.getUserName()+" Conerted "+ result);
    }

    @GetMapping("joke")
    public ResponseEntity<?> getjoke(@AuthenticationPrincipal UserDetails userDetails){
        User user=userService.getByUserName(userDetails.getUsername()).orElseThrow(()-> new RuntimeException("User not found with name "+userDetails.getUsername()));
        JokeApi joke=apiService.getRandomJoke();
        String haha="Hey "+user.getUserName()+" ! here is a joke for you \n"+joke.getSetup()+"😂😂 "+joke.getPunchline();
        return ResponseEntity.ok(haha);
    }

    // love quote is a premium-only feature on the API Ninjas service. so it is just a random qoute
    @GetMapping("lovequote")
    public ResponseEntity<?> getRandomQoute(@AuthenticationPrincipal UserDetails userDetails){
        User user=userService.getByUserName(userDetails.getUsername()).orElseThrow(()-> new RuntimeException("User not found with name "+userDetails.getUsername()));
        QuoteApi quote=apiService.getRandomLoveQuote();
        String quoteForYou= "Hey "+user.getUserName()+"💖💖💖.Here is a beautiful qoute for you\n"+quote.getQuote();
        return ResponseEntity.ok(quoteForYou);
    }
}
