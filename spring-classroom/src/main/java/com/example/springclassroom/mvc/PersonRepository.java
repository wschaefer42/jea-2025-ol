package com.example.springclassroom.mvc;

import jakarta.annotation.Nullable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    @Nullable
    Person findByName(String name);
}
