package com.example.springclassroom.ioc;

import jakarta.inject.Inject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Store {
    private final Item item1;
    private Item item2;

    @Inject
    public Store(@Qualifier("item1") Item item1) {
        this.item1 = item1;
    }

    @Inject
    public void setItem2(@Qualifier("item2") Item item2) {
        this.item2 = item2;
    }
}
