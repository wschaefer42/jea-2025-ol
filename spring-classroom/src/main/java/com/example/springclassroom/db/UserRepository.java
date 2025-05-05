package com.example.springclassroom.db;

import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Nullable
    User findByNameAndAgeAfter(String name, Integer age);
    @Query("select u from User u where lower(u.name) like %:name%")
    List<User> findByNameLike(String name);
}
