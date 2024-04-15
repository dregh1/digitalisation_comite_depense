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
public class TesteCnt {


    @Inject
    PeriodeService periodeService;
    @Inject
    DirectionService directionService;
    @Inject
    RubriqueService rubriqueService;

    @Inject
    SousrubriqueService sousrubriqueService;

    @Inject
    FournisseurService fournisseurService;

    @Inject
    DemandeService demandeService;

    @Inject
    BrouillonService brouillonService;

//    @GET()
//    @Path("permit-all")
//    @PermitAll
//    @Produces(MediaType.TEXT_PLAIN)
//    public String hello(@Context SecurityContext ctx) {
//        return getResponseString(ctx);
//    }
//hello + anonymous, isHttps: false, authScheme: null, hasJWT: false


    ///
//    @GET
//    @Path("roles-allowed")
//    @RolesAllowed({ "User", "Admin" })
//    @Produces(MediaType.TEXT_PLAIN)
//    public String helloRolesAllowed(@Context SecurityContext ctx) {
//        return getResponseString(ctx) + ", birthdate: " + jwt.getClaim("birthdate").toString();
//    }
//
//    ////////////////////
//    private String getResponseString(SecurityContext ctx) {
//        String name;
//        if (ctx.getUserPrincipal() == null) {
//            name = "anonymous";
//        } else if (!ctx.getUserPrincipal().getName().equals(jwt.getName())) {
//            throw new InternalServerErrorException("Principal and JsonWebToken names do not match");
//        } else {
//            name = ctx.getUserPrincipal().getName();
//        }
//        return String.format("hello + %s,"
//                        + " isHttps: %s,"
//                        + " authScheme: %s,"
//                        + " hasJWT: %s",
//                name, ctx.isSecure(), ctx.getAuthenticationScheme(), hasJwt());
//    }

//    private boolean hasJwt() {
//        return jwt.getClaimNames() != null;
//    }
    //////////////////////////////////////////////////////////////
    //Configuration de l'information sur la sécurité de l'extension de SmallRye JWT
    //////////////////////////////////////////////////////////////

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
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIdDirByName(@QueryParam("nom") String nomDir) {

        Long idDir ;
        if(nomDir!= null)
        {
            idDir = directionService.getIdDirByName(nomDir).getId();
        }else idDir= null;

        return Response.ok(idDir).build();
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

    //validation achat
//    @PUT

}


