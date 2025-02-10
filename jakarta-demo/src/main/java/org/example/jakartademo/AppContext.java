package org.example.jakartademo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.FacesConfig;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@FacesConfig
@ApplicationPath("api")
@ApplicationScoped
public class AppContext extends Application {
}
