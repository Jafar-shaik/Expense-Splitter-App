package com.jafar.expensesplitterapp.config;

import com.jafar.expensesplitterapp.api.response.CurrencyApi;
import com.jafar.expensesplitterapp.models.ConfigApiEntity;
import com.jafar.expensesplitterapp.repositories.ConfigApiEntityRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    private ConfigApiEntityRepository configApiEntityRepository;

    public Map<String , String > appCache;

    @PostConstruct
    public void init(){
        appCache=new HashMap<>();
        List<ConfigApiEntity> all = configApiEntityRepository.findAll();
        for (ConfigApiEntity configApiEntity : all) {
            appCache.put(configApiEntity.getKey(), configApiEntity.getValue());
        }
    }
}
