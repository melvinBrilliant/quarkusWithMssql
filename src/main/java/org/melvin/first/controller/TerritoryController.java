package org.melvin.first.controller;

import org.apache.http.HttpStatus;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;
import org.melvin.first.dto.RestResponse;
import org.melvin.first.dto.territory.UpsertTerritoryDto;
import org.melvin.first.service.implementation.TerritoryServiceImpl;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/territory")
public class TerritoryController {

    @Inject
    private TerritoryServiceImpl service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response index(@RestQuery @DefaultValue("1") Integer page,
                          @RestQuery @DefaultValue("100") Integer pageSize) {
        return Response.status(200).entity(service.findAll(page, pageSize)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(String id) {
        return Response.status(200).entity(service.findById(id)).build();
    }

    @PUT
    @Path("/upsert")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertTerritory(UpsertTerritoryDto dto) {
        try {
            return Response.accepted(service.saveOne(dto)).build();
        } catch (ConstraintViolationException e) {
            var error = e.getConstraintViolations();
            return Response
                    .status(HttpStatus.SC_UNPROCESSABLE_ENTITY)
                    .entity(new RestResponse<>(
                            null,
                            null,
                            null,
                            null,
                            null,
                            HttpStatus.SC_UNPROCESSABLE_ENTITY,
                            e.getConstraintViolations().toString()
                    ))
                    .build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response insertTerritory(@RestPath String id) {
        try {
            return Response.accepted(service.deleteOne(id)).build();
        } catch (NotFoundException e) {
            return Response
                    .status(HttpStatus.SC_NOT_FOUND)
                    .entity(new RestResponse<>(
                            null,
                            null,
                            null,
                            null,
                            null,
                            HttpStatus.SC_NOT_FOUND,
                            e.getMessage()
                    ))
                    .build();
        }
    }

}
