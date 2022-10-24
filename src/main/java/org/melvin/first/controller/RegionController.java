package org.melvin.first.controller;

import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;
import org.melvin.first.dto.region.RegionDto;
import org.melvin.first.service.implementation.RegionServiceImpl;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/region")
public class RegionController {

    @Inject
    private RegionServiceImpl service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RegionDto> index() {
        return service.findAllRaw();
    }

    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@RestQuery @DefaultValue("1") Integer page,
                            @RestQuery @DefaultValue("100") Integer pageSize) {
        return Response.status(200).entity(service.findAll(page, pageSize)).build();
    }

    @GET
    @Path("/id/{id}")
    public Response findById(@RestPath Long id) {
        return Response.status(200).entity(service.findById(id)).build();
    }

}
