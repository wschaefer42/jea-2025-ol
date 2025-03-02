package org.example.springdemo.basics.hello;

import jakarta.annotation.Nullable;
import jakarta.annotation.Resource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Resource
public interface HelloRepository extends CrudRepository<Hello, Long> {
    Hello findByName(String name);
    @Nullable
    @Query("select h from Hello h where h.name = ?1 and h.message = ?2")
    Hello findByAny(String name, String message);
}
