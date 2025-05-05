package com.example.controller;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/hello")
public class HelloController {
    @Inject
    Template hello;

    @GET
    public TemplateInstance hello() {
        return hello.data("title", "Quarkus", "name", "World");
    }
}
