package org.dre.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.dre.model.AvisCdg;
import org.dre.model.SessionCd;
import org.dre.service.AvisCdgService;
import org.dre.service.SessionCdService;

import java.util.List;

@Path("/cdg")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CdgCnt {

    @Inject
    AvisCdgService avisCdgService;

    @Inject
    SessionCdService sessionCdService;

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSessionCd() {
        // Récupérer les données depuis PostgreSQL
        List<AvisCdg> sessionCds = avisCdgService.getAll();
        return Response.ok(sessionCds).build();
    }

    //CREATION DE SESSION
    @POST
    @Path("/session/create")
    public Response createSessionCd(SessionCd sessionCd) {
        sessionCdService.createSessionCd(sessionCd);
        return Response.status(Response.Status.CREATED).entity(sessionCd).build();
    }
    @GET
    @Path("/avisCdg/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAvisCdg() {
        // Récupérer les données depuis PostgreSQL
        List<AvisCdg> sessionCds = avisCdgService.getAll();
        return Response.ok(sessionCds).build();
    }
}
