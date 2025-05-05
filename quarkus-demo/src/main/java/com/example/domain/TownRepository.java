package com.example.domain;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TownRepository implements PanacheRepository<Town> {
    public Town findByName(String name, String countryCode) {
        return find("name = ?1 and countryCode = ?2", name, countryCode).firstResult();
    }

    public Town createIfNotFound(String name, String countryCode) {
        var town = findByName(name, countryCode);
        if (town == null) {
            town = new Town();
            town.name = name;
            town.countryCode = countryCode;
            persist(town);
        }
        return town;
    }
}
