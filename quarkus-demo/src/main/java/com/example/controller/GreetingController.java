package com.example.controller;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.Path;

@Path("/greeting")
public class GreetingController {
    @CheckedTemplate(basePath = "greeting")
    public static class Templates {
        public static native TemplateInstance hello(String title, String name);
    }
}
