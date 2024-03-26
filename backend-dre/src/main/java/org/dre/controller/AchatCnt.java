package org.dre.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.dre.model.Avis_achat;
import org.dre.model.Avis_achat;
import org.dre.model.Demande;
import org.dre.model.Personnel;
import org.dre.service.Avis_achatService;
import org.dre.service.Avis_achatService;
import org.dre.service.DemandeService;
import org.dre.service.Periode_dmdService;

@Path("/achat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AchatCnt {

    @Inject
    Avis_achatService avis_achatService;
    @Inject
    DemandeService demandeService;

    //COMMENTAIRE ACHAT
    @POST
    @Path("/commentaire/create")
    public Response createCommentaireAchat(Avis_achat avis_achat) {

        avis_achatService.create(avis_achat);
        return Response.status(Response.Status.CREATED).entity(avis_achat).build();
    }

    //VALIDATION ACHAT
//    @POST
//    @Path("/validate")
//    public Response createCommentaireAchat(Avis_achat avis_achat) {
//
//        avis_achatService.create(avis_achat);
//        return Response.status(Response.Status.CREATED).entity(avis_achat).build();
//    }

    @PUT
    @Path("/validateDmd/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
    public Response validateDemande(@PathParam("id") Long id) {

        //find by id demande
        Demande d = Demande.findById(id);

        d.setIs_valdby_ach(true);
        demandeService.updateDemande(d);

        return Response.ok(d).build();
    }


}
