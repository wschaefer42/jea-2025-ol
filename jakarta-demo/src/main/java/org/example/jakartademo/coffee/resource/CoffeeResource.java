package org.example.jakartademo.coffee.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import org.example.jakartademo.coffee.persistance.Coffee;
import org.example.jakartademo.coffee.persistance.CoffeeRepository;

import java.util.List;

@Path("/coffees")
public class CoffeeResource {
    @Inject
    private CoffeeRepository coffeeService;

    @GET
    public List<Coffee> getAll(@DefaultValue("") @QueryParam("name") String name) {
        if (StringUtils.isBlank(name)) {
            return coffeeService.findAll();
        } else {
            return coffeeService.queryByName(name);
        }
    }

    @GET
    @Path("/{id}")
    public Response getCoffee(@PathParam("id") Long id) {
        var coffee = coffeeService.findById(id);
        // The compiler gives a warning that we checked a Nullable not
        // System.out.println(coffee.toString());
        if (coffee == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(coffee).build();
        }
    }
}
