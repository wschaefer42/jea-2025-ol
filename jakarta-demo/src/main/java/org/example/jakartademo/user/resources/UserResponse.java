package org.example.jakartademo.user.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.example.jakartademo.user.persistence.User;
import org.example.jakartademo.user.persistence.UserRepository;

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
        return userRepository.saveUser(user);
    }
}
