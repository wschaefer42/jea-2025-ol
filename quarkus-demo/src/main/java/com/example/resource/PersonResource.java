package com.example.resource;

import com.example.domain.Person;
import com.example.domain.TownRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.jboss.resteasy.reactive.RestQuery;

import java.util.List;

@Path( "/api/persons")
public class PersonResource {
    public record PersonRecord(String firstName, String lastName, String town) {}

    @Inject
    TownRepository townRepository;

    @GET
    public List<Person> list() {
        return Person.listAll();
    }

    @GET
    @Path("/{id}")
    public Person get(@PathParam("id") Long id) {
        return Person.findById(id);
    }

    @GET
    @Path("/search")
    public List<Person> search(@RestQuery("firstName") String firstName, @RestQuery("lastName") String lastName) {
        return Person.listByNames(firstName, lastName);
    }

    @POST
    @Transactional
    public Person create(PersonRecord personRecord) {
        var town = townRepository.createIfNotFound(personRecord.town, "CHE");
        if (Person.findByNameAndTown(personRecord.firstName, personRecord.lastName, town.name) == null) {
            var person = Person.of(personRecord.firstName, personRecord.lastName, null, town);
            person.persist();
            return person;
        }
        throw new IllegalArgumentException("Person already exists");
    }
}
