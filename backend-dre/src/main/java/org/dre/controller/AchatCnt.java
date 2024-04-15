package org.dre.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.dre.model.AvisAchat;
import org.dre.model.AvisCdg;
import org.dre.model.Demande;
import org.dre.repository.AvisAchatRepository;
import org.dre.service.AvisAchatService;
import org.dre.service.DemandeService;

import java.util.List;

@Path("/achat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AchatCnt {
    @Inject
    AvisAchatRepository avisAchatRepository;

    @Inject
    AvisAchatService avisAchatService;
    @Inject
    DemandeService demandeService;

    //COMMENTAIRE ACHAT
    @POST
    @Path("/avisAchat/create")
    public Response createCommentaireAchat(AvisAchat avis_achat) {

        avisAchatService.create(avis_achat);
        return Response.status(Response.Status.CREATED).entity(avis_achat).build();
    }
    

    @PUT
    @Path("/validateDmd/{id}")
    public Response validateDemande(@PathParam("id") Long id) {

        //find by id demande
        Demande d = Demande.findById(id);

        d.setValidationAchat(true);
        demandeService.updateDemande(d);

        return Response.ok(d).build();
    }

    //get all avis achat
    @GET
    @Path("/avisaAchat/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAvisAchat() {
        // Récupérer les données depuis PostgreSQL
        List<AvisAchat> avisAchat = avisAchatService.getAll();
        return Response.ok(avisAchat).build();
    }

    //get avis achat by id
    @GET
    @Path("avisAchat/{id}")
    public AvisAchat getAvisAchatById(@PathParam("id") Long id) {
        return avisAchatRepository.findById(id);
    }

    //get avis achat by id demande
    @GET
    @Path("avisAchatByIdDemande/{id}")
    public AvisAchat getAvisAchatByIdDemande(@PathParam("id") Long id) {
        return avisAchatService.getAvisAchatByIdDemande(id);
    }




}
