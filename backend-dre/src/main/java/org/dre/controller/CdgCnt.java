package org.dre.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.dre.model.Avis_cdg;
import org.dre.model.SessionCd;
import org.dre.service.Avis_cdgService;
import org.dre.service.SessionCdService;

import java.util.List;

@Path("/cdg")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CdgCnt {

    @Inject
    Avis_cdgService Avis_cdgService;

    @Inject
    SessionCdService sessionCdService;

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSessionCd() {
        // Récupérer les données depuis PostgreSQL
        List<Avis_cdg> sessionCds = Avis_cdgService.getAll();
        return Response.ok(sessionCds).build();
    }

    //CREATION DE SESSION
    @POST
    @Path("/session/create")
    public Response createSessionCd(SessionCd sessionCd) {
//     { daty: "2004-12-12", deviseEur: 1111, deviseUsd: 1, deviseGbp: 11 }
        /*
					ref varchar(9) not null,
					date_cloture timestamp not null,
					is_deleted boolean default false,
					taux_eur decimal(20,3) not null,
					taux_usd decimal(20,3) not null,
					taux_gbp decimal(20,3) not null,
					taux_mga decimal(20,3) not null,
        * */


        sessionCdService.createSessionCd(sessionCd);
        return Response.status(Response.Status.CREATED).entity(sessionCd).build();
    }
    @GET
    @Path("/avis_cdg/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAvis_cdg() {
        // Récupérer les données depuis PostgreSQL
        List<Avis_cdg> sessionCds = Avis_cdgService.getAll();
        return Response.ok(sessionCds).build();
    }
}
