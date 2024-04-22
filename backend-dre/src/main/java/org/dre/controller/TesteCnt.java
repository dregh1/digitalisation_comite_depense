package org.dre.controller;

import io.quarkus.security.Authenticated;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.dre.model.*;
import org.dre.model.Periode;
import org.dre.service.*;
//import org.eclipse.microprofile.jwt.JsonWebToken;
    //mila esorina reto
//import org.keycloak.AuthorizationContext;
//import org.keycloak.KeycloakPrincipal;
//import org.keycloak.KeycloakSecurityContext;
//import org.keycloak.representations.AccessToken;

import java.util.List;

@Path("/teste")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class TesteCnt {


    @Inject
    PeriodeService periodeService;
    @Inject
    DirectionService directionService;
    @Inject
    RubriqueService rubriqueService;
    @Inject
    DetailDemandeService detailDemandeService;


    @Inject
    FournisseurService fournisseurService;

    @Inject
    DemandeService demandeService;

    @Inject
    SessionCdService sessionCdService;


//    TEST CRUD
    @GET
    @Path("/periode/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllperiode() {
        // Récupérer les données depuis PostgreSQL
        List<Periode> sessionCds = periodeService.getAll ();
        return Response.ok(sessionCds).build();
    }

    @GET
    @Path("/demande/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlldemande() {
        // Récupérer les données depuis PostgreSQL
        List<Demande> demande = demandeService.getAll ();
        return Response.ok(demande).build();
    }

    @GET
    @Path("/fournisseur/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFournisseur() {
        // Récupérer les données depuis PostgreSQL
        List<Fournisseur> rubriques = fournisseurService.getAll ();
        return Response.ok(rubriques).build();
    }




    @POST
    @Path("/post/create")
    public Response createSessionCd(Periode sessionDd) {



        periodeService.create(sessionDd);
        return Response.status(Response.Status.CREATED).entity(sessionDd).build();
    }

    @POST
    @Path("demande/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDemande(Demande demande) {
        demandeService.create(demande);
        return Response.status(Response.Status.CREATED).entity(demande).build();
    }

    //get id direction by name of direction
    @GET
    @Path("/getIdDir")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIdDirByName(@QueryParam("nom") String nomDir) {

        Long idDir = null;
        Direction d= null;
        if(nomDir!= null)
        {
            d = directionService.getIdDirByName(nomDir);
        }



        return Response.ok(d).build();
    }
    @POST
    @Path("/getIdDir")
    public Response createUser(String nomDir) {
        Long idDir ;

        if(nomDir!= null)
        {
            idDir = directionService.getIdDirByName(nomDir).getId();
        }else idDir= null;

        return Response.ok(idDir).build();
    }
//GET ALL SESSION
    @GET
    @Path("/session/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSession() {
        // Récupérer les données depuis PostgreSQL
        List<SessionCd> session = sessionCdService.getAll ();
        return Response.ok(session).build();
    }
//GET SESSION ACTIVE
    @GET
    @Path("/session/getByDir/{idDirection}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSessionActive(@PathParam("idDirection") Integer idDirection) {
        // Récupérer les données depuis PostgreSQL
        SessionCd session = sessionCdService.getSessionActive(idDirection);
        return Response.ok(session).build();
    }
//GET ALL DETAILDEMANDE
    @GET
    @Path("/detailDemande/get")
    @RolesAllowed({"PRS","CDG","CDG"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDetailDemande() {
        // Récupérer les données depuis PostgreSQL
        List<DetailDemande> detailDemande = detailDemandeService.getAll();
        return Response.ok(detailDemande).build();
    }
    @GET
    @Path("/detailDemande/{id}")
    @RolesAllowed({"PRS","CDG","CDG"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDetailDemande(@PathParam( "id") Long id) {
        // Récupérer les données depuis PostgreSQL
        DetailDemande detailDemande = detailDemandeService.getDetailDemandeById(id);
        return Response.ok(detailDemande).build();
    }

}


