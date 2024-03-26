package org.dre.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.dre.model.*;
import org.dre.service.*;

import java.util.List;

@Path("/prescripteur")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PrescripteurCnt {
    @Inject
    Periode_dmdService periodeDmdService;
    @Inject
    Avis_achatService avis_achatService;
    @Inject
    Active_dmdService active_dmdService;
    @Inject
    DeviseService deviseService;

    @Inject
    DemandeService demandeService;
    @Inject
    ReferenceService referenceService;
    @Inject
    BrouillonService brouillonService;
    @Inject
    FournisseurService fournisseurService;

    @POST
    @Path("demande/create")
    public Response createDemande(Demande demande) {
        demandeService.create(demande);
        return Response.status(Response.Status.CREATED).entity(demande).build();
    }
    @GET
    @Path("/periode/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllperiode() {
        // Récupérer les données depuis PostgreSQL
        List<Periode_dmd> sessionCds = periodeDmdService.getAll ();
        return Response.ok(sessionCds).build();
    }

    @GET
    @Path("/fournisseur/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFournisseur() {
        // Récupérer les données depuis PostgreSQL
        List<Fournisseur> rubriques = fournisseurService.getAll ();
        return Response.ok(rubriques).build();
    }

    @GET
    @Path("/active_dmd/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllActive_dmd() {
        // Récupérer les données depuis PostgreSQL
        List<Active_dmd> active_dmds = active_dmdService.getAll ();
        return Response.ok(active_dmds).build();
    }

    @GET
    @Path("/brouillon/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBrouillon() {
        // Récupérer les données depuis PostgreSQL
        List<Brouillon> brouillons = brouillonService.getAll ();
        return Response.ok(brouillons).build();
    }

    @GET
    @Path("/avis_achat/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvis_achat() {
        // Récupérer les données depuis PostgreSQL
        List<Avis_achat> avis_achat = avis_achatService.getAll ();
        return Response.ok(avis_achat).build();
    }

    @GET
    @Path("/devise/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDevise() {
        // Récupérer les données depuis PostgreSQL
        List<Devise> devises = deviseService.getAll ();
        return Response.ok(devises).build();
    }

    @GET
    @Path("/reference/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReference() {
        // Récupérer les données depuis PostgreSQL
        List<Reference> references = referenceService.getAll ();
        return Response.ok(references).build();
    }


}
