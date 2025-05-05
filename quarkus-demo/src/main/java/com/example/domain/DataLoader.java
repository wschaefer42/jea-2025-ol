package com.example.domain;

import io.quarkus.logging.Log;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class DataLoader {
    @Inject
    TownRepository townRepository;

    @Transactional
    public void onStart(@Observes StartupEvent ev) {
        var count = 0;
        var town = townRepository.createIfNotFound("Zürich", "CHE");
        for (var person : List.of(
                Person.of("Alice", "Peter", null, town),
                Person.of("Bob", "Smith", null, town),
                Person.of("Werner", "Kummer", null, town)
        )) {
            Person.persist(person);
            count++;
        }
        Log.infof("Loaded %d persons", count);
    }
}
