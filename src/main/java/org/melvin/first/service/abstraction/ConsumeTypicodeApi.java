package org.melvin.first.service.abstraction;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.melvin.first.dto.typicode.UserDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RegisterRestClient(configKey = "typicode")
public interface ConsumeTypicodeApi {

    @Path("/users")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<UserDto> getUsers();
}
