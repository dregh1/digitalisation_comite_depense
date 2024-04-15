package org.dre.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.dre.model.AvisAchat;
import org.dre.model.Demande;
import org.dre.service.AvisAchatService;
import org.dre.service.DemandeService;

import java.util.List;

@Path("/achat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AchatCnt {

    @Inject
    AvisAchatService avis_achatService;
    @Inject
    DemandeService demandeService;

    //COMMENTAIRE ACHAT
    @POST
    @Path("/avisAchat/create")
    public Response createCommentaireAchat(AvisAchat avis_achat) {

        avis_achatService.create(avis_achat);
        return Response.status(Response.Status.CREATED).entity(avis_achat).build();
    }

    //VALIDATION ACHAT
//    @POST
//    @Path("/validate")
//    public Response createCommentaireAchat(AvisAchat avis_achat) {
//
//        avis_achatService.create(avis_achat);
//        return Response.status(Response.Status.CREATED).entity(avis_achat).build();
//    }

    @PUT
    @Path("/validateDmd/{id}")
    public Response validateDemande(@PathParam("id") Long id) {

        //find by id demande
        Demande d = Demande.findById(id);

        d.setValidationAchat(true);
        demandeService.updateDemande(d);

        return Response.ok(d).build();
    }

//    @POST
//    @Path("/avis_achat/get")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createCommentaireAchat(AvisAchat avis_achat) {
//
//        avis_achatService.create(avis_achat);
//        return Response.status(Response.Status.CREATED).entity(avis_achat).build();
//    }


}
