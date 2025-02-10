package org.example.jakartademo.tests;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UserRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public User createUser(User user) {
        em.persist(user);
        return user;
    }

    public List<User> findAll() {
        return em.createQuery("SELECT u from User u", User.class).getResultList();
    }

}
