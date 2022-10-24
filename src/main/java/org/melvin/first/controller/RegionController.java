package org.melvin.first.controller;

import org.apache.http.HttpStatus;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;
import org.melvin.first.dto.RestResponse;
import org.melvin.first.dto.region.RegionDto;
import org.melvin.first.dto.region.UpsertRegionDto;
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@RestPath Integer id) {
        return Response.status(200).entity(service.findById(id)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertNewRegion(UpsertRegionDto dto) {
        if (dto.getId() != null) {
            dto.setId(0);
        }
        return Response.accepted(service.saveOne(dto)).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRegion(UpsertRegionDto dto) {
        return Response.accepted(service.saveOne(dto)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRegion(Integer id) {
        try {
            return Response.accepted(service.deleteOne(id)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(400).entity(
                            new RestResponse<>(
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    HttpStatus.SC_BAD_REQUEST,
                                    e.getMessage()
                            )
                    )
                    .build();
        } catch (NotFoundException e) {
            return Response.status(404).entity(
                            new RestResponse<>(
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    HttpStatus.SC_BAD_REQUEST,
                                    e.getMessage()
                            )
                    )
                    .build();
        }
    }

}
