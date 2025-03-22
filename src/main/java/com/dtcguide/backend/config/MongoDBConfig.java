package com.dtcguide.backend.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;


@Configuration
public class MongoDBConfig {

    @Value("${DATABASE}")
    private String DATABASE;

    @Value("${DATABASE_URI}")
    private String DATABASE_URI;


    @Bean
    public MongoClient mongoClient(){
        return MongoClients.create(DATABASE_URI);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient){
//        System.out.println(System.getenv());
        return new MongoTemplate(mongoClient, DATABASE);
    }

}
