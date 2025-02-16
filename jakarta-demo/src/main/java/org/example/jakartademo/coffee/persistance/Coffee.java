package org.example.jakartademo.coffee.persistance;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "coffees")
@Getter @Setter
public class Coffee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    @Column(unique = true, nullable = false)
    private String name;
    @Min(0) @Max(100)
    @Column(nullable = false)
    private Double price = 0.0;
    @Column(length = 20, columnDefinition = "varchar(20) default 'OTHER'")
    @Enumerated(EnumType.STRING)
    private CoffeeBeanType type = CoffeeBeanType.OTHER;

    @Override
    public String toString() {
        return String.format("Coffee{id=%d, name='%s'}", id, name);
    }

    public static Coffee of(String name, Double price, CoffeeBeanType type) {
        var coffee = new Coffee();
        coffee.name = name;
        coffee.price = price;
        coffee.type = type;
        return coffee;
    }
}
