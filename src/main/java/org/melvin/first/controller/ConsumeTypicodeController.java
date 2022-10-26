package org.melvin.first.controller;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.melvin.first.dto.typicode.UserDto;
import org.melvin.first.service.abstraction.ConsumeTypicodeApi;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/typicode")
public class ConsumeTypicodeController {

    @Inject
    @RestClient
    private ConsumeTypicodeApi service;

    @GET
    @Path("/users")
    public List<UserDto> getUsers() {
        return service.getUsers();
    }
}
