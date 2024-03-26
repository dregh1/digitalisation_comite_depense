package org.dre.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.dre.model.*;
import org.dre.model.Periode_dmd;
import org.dre.service.*;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.List;

@Path("/teste")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TesteCnt {

    @Inject
    JsonWebToken jwt;
    @Inject
    Periode_dmdService periodeDmdService;
    @Inject
    RubriqueService rubriqueService;

    @Inject
    SousrubriqueService sousrubriqueService;

    @Inject
    FournisseurService fournisseurService;
    @Inject
    Avis_achatService avis_achatService;

    @Inject
    Active_dmdService active_dmdService;

    @Inject
    DemandeService demandeService;

    @Inject
    BrouillonService brouillonService;

    @GET()
    @Path("permit-all")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@Context SecurityContext ctx) {
        return getResponseString(ctx);
    }
//hello + anonymous, isHttps: false, authScheme: null, hasJWT: false


    ///
    @GET
    @Path("roles-allowed")
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.TEXT_PLAIN)
    public String helloRolesAllowed(@Context SecurityContext ctx) {
        return getResponseString(ctx) + ", birthdate: " + jwt.getClaim("birthdate").toString();
    }

    ////////////////////
    private String getResponseString(SecurityContext ctx) {
        String name;
        if (ctx.getUserPrincipal() == null) {
            name = "anonymous";
        } else if (!ctx.getUserPrincipal().getName().equals(jwt.getName())) {
            throw new InternalServerErrorException("Principal and JsonWebToken names do not match");
        } else {
            name = ctx.getUserPrincipal().getName();
        }
        return String.format("hello + %s,"
                        + " isHttps: %s,"
                        + " authScheme: %s,"
                        + " hasJWT: %s",
                name, ctx.isSecure(), ctx.getAuthenticationScheme(), hasJwt());
    }

    private boolean hasJwt() {
        return jwt.getClaimNames() != null;
    }
    //////////////////////////////////////////////////////////////
    //Configuration de l'information sur la sécurité de l'extension de SmallRye JWT
    //////////////////////////////////////////////////////////////

//    TEST CRUD
    @GET
    @Path("/periode/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllperiode() {
        // Récupérer les données depuis PostgreSQL
        List<Periode_dmd> sessionCds = periodeDmdService.getAll ();
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
    @Path("/rubrique/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRubrique() {
        // Récupérer les données depuis PostgreSQL
        List<Rubrique> rubriques = rubriqueService.getAll ();
        return Response.ok(rubriques).build();
    }
    @GET
    @Path("/sousrubrique/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSousRubrique() {
        // Récupérer les données depuis PostgreSQL
        List<Sousrubrique> rubriques = sousrubriqueService.getAll ();
        return Response.ok(rubriques).build();
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
    public Response createSessionCd(Periode_dmd sessionDd) {



        periodeDmdService.create(sessionDd);
        return Response.status(Response.Status.CREATED).entity(sessionDd).build();
    }

    @POST
    @Path("demande/create")
    public Response createDemande(Demande demande) {
        demandeService.create(demande);
        return Response.status(Response.Status.CREATED).entity(demande).build();
    }
}


