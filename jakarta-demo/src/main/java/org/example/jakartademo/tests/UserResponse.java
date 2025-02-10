package org.example.jakartademo.tests;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import java.util.List;

@Path("/users")
public class UserResponse {
    @Inject
    private UserRepository userRepository;

    @GET
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @POST
    public User create(String name) {
        var user = new User();
        user.setUsername(name);
        user.setUsername(name + "@gmail.com");
        return userRepository.createUser(user);
    }
}
