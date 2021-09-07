package com.example.resource;

import com.example.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/{user}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Path("/{user_id}")
    public Response getUser(@PathParam("user_id") String userId) throws Exception {
        return Response.ok()
                .entity(userService.get(userId))
                .build();
    }

}
