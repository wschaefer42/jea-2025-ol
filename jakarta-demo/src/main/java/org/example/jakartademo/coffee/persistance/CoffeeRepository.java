package org.example.jakartademo.coffee.persistance;

import jakarta.annotation.Nullable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import org.example.jakartademo.common.NotFoundException;

import java.util.List;

/// ## CoffeeService
/// **Note** To distinguish between the manual implemented *Repository* and the generated `Repository`
/// we name the manual created **Repository** as `Service`
///
/// #### Stateless or Singleton
/// We can either declare the Service as `Stateless` or `ApplicationScoped`. With the `Stateless` annotation,
/// it is up to the system to create only one instance, because it is stateless. With the CDI qualifier
/// `ApplicationScoped` we instruct the runtime environment to create a singleton the first time the class
/// is instantiated. The instance will be managed through the CDI Container.
///
/// ### Logging
/// We are using the Lombok project logging capability added by the annotation `Log`
///
/// ### Persistence
/// With the annotation `PersistenceContext` we add the persistence definition from the `persistence.xml` file.
/// Without a given name, the `default` persistence unit is used.
///
/// #### Save or Add/Update
/// Using a single method for `add` and `update` the entity corresponds to the Spring behavior. Distinguishing between
/// create or update the entity is more the Jakarta way. The new Jakarta Data `BasicRepository` also offers a `save` method
/// to store (create or update) the entity depending on the ID value.
@Log
@ApplicationScoped
public class CoffeeRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Coffee save(Coffee coffee) {
        log.info(String.format("Saving %s", coffee));
        if (coffee.getId() == null) {
            em.persist(coffee);
        } else {
            em.merge(coffee);
        }
        return coffee;
    }

    /// ## Nullable
    /// There is a big discussion in the community about the null and the usage of `Optional`
    /// to prevent accessing a null object. Other languages like **Kotlin** can deal with.
    /// My opinion is not to use `Optional` in Java. If we use it, we should use it everywhere.
    /// It makes things complicate if once a `Optional` is returned and once not. JetBrains and later
    /// Jakarta introduce the following annotation. With this annotation, the compiler will check if
    /// we probe the object is null or not.
    public @Nullable Coffee findById(Long id) {
        return em.find(Coffee.class, id);
    }

    public @Nullable Coffee findByName(String name) {
        try {
            return em.createQuery("SELECT c FROM Coffee c WHERE lower(c.name) = lower(:name)", Coffee.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    // Usually findAll without a `Pager` or any limitation is not a good idea
    public List<Coffee> findAll() {
        return em.createQuery("SELECT c FROM Coffee c", Coffee.class).getResultList();
    }

    public List<Coffee> queryByName(String name) {
        if (name.contains("*")) {
            name = name.replace("*", "%");
        }
        return em.createQuery("SELECT c FROM Coffee c WHERE lower(c.name) LIKE lower(:name)", Coffee.class)
                .setParameter("name", name)
                .getResultList();
    }

    public void delete(long id) {
        if (em.contains(findById(id))) {
            em.remove(findById(id));
        } else {
            throw new NotFoundException("Coffee with ID %d not found", id);
        }
    }
}
