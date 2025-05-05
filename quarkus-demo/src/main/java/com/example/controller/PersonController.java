package com.example.controller;

import com.example.domain.Person;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestForm;

import java.util.List;

@Path( "/persons")
public class PersonController {
    @CheckedTemplate(basePath = "")
    public static class Templates {
        public static native TemplateInstance person(List<Person> persons, Person person);
        public static native TemplateInstance person$form(Person person);
    }

    @GET
    public TemplateInstance index() {
        return Templates.person(Person.listAll(), null);
    }

    @GET
    @Path("/{id}")
    public TemplateInstance person(Long id) {
        return Templates.person$form(Person.findById(id));
    }

    @GET
    @Path("/new")
    public TemplateInstance newPerson() {
        return Templates.person$form(new Person());
    }

    @POST
    public TemplateInstance save(@RestForm String firstName, @RestForm String lastName) {
        return Templates.person(Person.listAll(), Person.of(firstName, lastName, null, null));
    }
}
