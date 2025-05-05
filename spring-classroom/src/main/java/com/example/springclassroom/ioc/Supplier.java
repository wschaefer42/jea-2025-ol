package com.example.springclassroom.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Supplier {
    @Bean
    @Qualifier("item1")
    public Item getItem1() {
        return new Item("item1");
    }

    @Bean
    @Qualifier("item2")
    public Item getItem2() {
        return new Item("item2");
    }
}
