package com.example.demo2.config;



import org.json.simple.parser.JSONParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
	
    @Bean
    public JSONParser jsonParser(){
        return new JSONParser();
    }
}

