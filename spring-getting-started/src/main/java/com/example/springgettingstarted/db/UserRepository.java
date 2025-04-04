package com.example.springgettingstarted.db;

import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Nullable
    @Query("from User where name = :name or email = :name")
    User findByNameOrEmail(@Param("name") String name);
    @Nullable
    List<User> findByNameContainsIgnoreCase(@Param("name") String name);
}
