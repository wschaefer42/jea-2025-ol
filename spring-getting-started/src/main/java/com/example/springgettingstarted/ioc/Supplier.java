package com.example.springgettingstarted.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.logging.Logger;

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

    @Bean
    public Logger getLogger() {
        return Logger.getLogger(Supplier.class.getName());
    }
}
