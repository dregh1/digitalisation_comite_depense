package org.dre.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.dre.model.AvisCdg;
import org.dre.model.Brouillon;
import org.dre.model.Demande;
import org.dre.model.SessionCd;
import org.dre.repository.AvisCdgRepository;
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
    AvisCdgRepository avisCdgRepository;

    @Inject
    SessionCdService sessionCdService;


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
        List<AvisCdg> avisCdg = avisCdgService.getAll();
        return Response.ok(avisCdg).build();
    }

    @GET
    @Path("avisCdg/{id}")
    public AvisCdg getAvisCdgById(@PathParam("id") Long id) {
        return avisCdgRepository.findById(id);
    }

    @GET
    @Path("avisCdgByIdDemande/{id}")
    public AvisCdg getAvisCdgByIdDemande(@PathParam("id") Long id) {
        return avisCdgService.getAvisCdgByIdDemande(id);
    }


    //creation validation,ajout commentaire, ajout montantbudgetmensuel | montantengage
    @POST
    @Path("/avisCdg/create")
    public Response createAvisCdg(AvisCdg avisCdg) {
        avisCdgService.create(avisCdg);
        return Response.status(Response.Status.CREATED).entity(avisCdg).build();
    }

    //UPDATE AVISCDG
    @PUT
    @Path("avisCdg/{id}")
    public Response updateAvisCdg(@PathParam("id") Long id, AvisCdg avisCdg) {
        avisCdg.setId(id); // Assure que l'ID de l'utilisateur est correctement défini
        avisCdgService.updateAvisCdg(avisCdg);
        return Response.ok(avisCdg).build();
    }


}
