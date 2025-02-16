package org.example.jakartademo.coffee.views;

import org.example.jakartademo.coffee.persistance.Coffee;
import org.example.jakartademo.coffee.persistance.CoffeeBeanType;

public record CoffeeData(long id, String name, Double price, CoffeeBeanType beanType) {
    public CoffeeData(Coffee entity) {
        this(entity.getId(), entity.getName(), entity.getPrice(), entity.getType());
    }
}
