package org.example.jakartademo.user.persistence;

import jakarta.annotation.Nullable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.jakartademo.common.AlreadyExistsException;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class UserRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    @SuppressWarnings("all")
    public User saveUser(User user) {
        if (user.getId() == null) {
            if (findByName(user.getUsername()) == null) {
                em.persist(user);
            } else {
                throw new AlreadyExistsException("User %s already exists", user.getUsername());
            }
        } else {
            em.merge(user);
        }
        return user;
    }

    public List<User> findAll() {
        return em.createQuery("SELECT u from User u", User.class).getResultList();
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Nullable
    public LocalDate getLastModified() {
        try {
            return em.createQuery("SELECT MAX(u.modifiedAt) FROM User u", LocalDate.class).getSingleResult();
        } catch (NoResultException ignored) {
            return null;
        }
    }

    @Nullable
    public User findByName(String name) {
        return em.createQuery("select u from User u where u.username = ?1", User.class)
                .setParameter(1, name)
                .getResultList()
                .stream().findFirst().orElse(null);
    }

    @Nullable
    public User findByEmail(String email) {
        try {
            return em.createQuery("select u from User u where u.email = :email", User.class).getSingleResult();
        } catch (NoResultException ignored) {
            return null;
        }
    }

}
