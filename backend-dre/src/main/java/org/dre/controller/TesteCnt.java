package org.dre.controller;

//import io.quarkus.mailer.Mail;

//import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.security.Authenticated;
import io.smallrye.common.annotation.Blocking;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.core.UriBuilder;

import org.dre.model.*;
import org.dre.model.Periode;
import org.dre.service.*;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.RoleRepresentation;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Path("/teste")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class TesteCnt {

    @Inject
    JsonWebToken jwt;
    @Inject
    Mailer mailer;

    @Inject
    TitreDemandeService titredemandeService;
    @Inject
    PeriodeService periodeService;
    @Inject
    DirectionService directionService;
    @Inject
    AvisCdgService avisCdgService;

    @Inject
    AvisAchatService avisAchatService;
    @Inject
    ValidationService validationService;
    @Inject
    DetailDemandeService detailDemandeService;

    @Inject
    SessionCdService sessionCdService;


    @Inject
    DemandeService demandeService;

    @Inject
    ActiveService activeService;


    //    TEST CRUD
    @GET
    @Path("/periode/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllperiode() {
        // Récupérer les données depuis PostgreSQL
        List<Periode> sessionCds = periodeService.getAll();
        return Response.ok(sessionCds).build();
    }

    @GET
    @Path("/demande/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlldemande() {
        // Récupérer les données depuis PostgreSQL
        List<Demande> demande = demandeService.getAll();
        return Response.ok(demande).build();
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
    @RolesAllowed({"PRS", "CDG", "ACH"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIdDirByName(@QueryParam("nom") String nomDir) {

        Long idDir = null;
        Direction d = null;
        if (nomDir != null) {
            d = directionService.getIdDirByName(nomDir);
        }


        return Response.ok(d).build();
    }

    //get direction tout
    @GET
    @Path("/getDirection")
    @RolesAllowed({"PRS", "ACH", "CDG"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDirrection() {
        List<Direction> session = directionService.getAll();
        return Response.ok(session).build();
    }

    @POST
    @Path("/getIdDir")
    public Response createUser(String nomDir) {
        Long idDir;

        if (nomDir != null) {
            idDir = directionService.getIdDirByName(nomDir).getId();
        } else idDir = null;

        return Response.ok(idDir).build();
    }

    //GET ALL SESSION
    @GET
    @Path("/session/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSession() {
        // Récupérer les données depuis PostgreSQL
        List<SessionCd> session = sessionCdService.getAll();
        return Response.ok(session).build();
    }

    //GET ALL DETAILDEMANDE
    @GET
    @Path("/detailDemande/get")
    @RolesAllowed({"PRS", "CDG", "ACH"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDetailDemande() {
        // Récupérer les données depuis PostgreSQL
        List<DetailDemande> detailDemande = detailDemandeService.getAll();
        return Response.ok(detailDemande).build();
    }

    @GET
    @Path("/detailDemande/{id}")
    @RolesAllowed({"PRS", "CDG", "ACH"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDetailDemande(@PathParam("id") Long id) {
        // Récupérer les données depuis PostgreSQL
        DetailDemande detailDemande = detailDemandeService.getDetailDemandeById(id);
        return Response.ok(detailDemande).build();
    }

    @GET
    @Path("/validation/get")
    @RolesAllowed({"PRS", "CDG", "ACH"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllValidation() {
        // Récupérer les données depuis PostgreSQL
        List<Validation> validation = validationService.getAll();
        return Response.ok(validation).build();
    }

    @GET
    @Path("/validation/{id}")
    @RolesAllowed({"PRS", "CDG", "ACH"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getValidationById(@PathParam("id") Long id) {
        // Récupérer les données depuis PostgreSQL
        Validation validation = validationService.getById(id);
        return Response.ok(validation).build();
    }

    //GET ALL CONSULTATION
    @GET
    @Path("/detailDemande/search")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"PRS", "CDG", "ACH"})
    public Response getDetails(
            @QueryParam("idDirection") @DefaultValue("") String idDirection,
            @QueryParam("statut") @DefaultValue("") String statut,
            @QueryParam("motif") @DefaultValue("") String motif,
            @QueryParam("dateDebut") @DefaultValue("") String dateDebut,
            @QueryParam("dateFin") @DefaultValue("") String dateFin,
            @QueryParam("session") @DefaultValue("") String session,
            @QueryParam("idFournisseur") @DefaultValue("") String idFournisseur,
            @QueryParam("validAchat") @DefaultValue("") String validAchat,
            @QueryParam("validCdg") @DefaultValue("") String validCdg

    ) {

        // Récupérer les données depuis PostgreSQL
        List<DetailDemande> detailDemandes = detailDemandeService.chercher(idDirection, motif, session, idFournisseur, dateDebut, dateFin, statut, validAchat, validCdg);
        return Response.ok(detailDemandes).build();

    }

//    @GET
//    @Path("/detailDemande/get")
//    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"PRS","CDG","ACH"})
//    public Response getDetailFiltrer(
//            @QueryParam("idDirection") @DefaultValue("")String idDirection,
//            @QueryParam("sessionCd")@DefaultValue("") String  sessionCd
//    )  {
//
//        // Récupérer les données depuis PostgreSQL
//        List<DetailDemande> detailDemandes  = detailDemandeService.chercher (idDirection,motif,session,idFournisseur,dateDebut,dateFin,statut);
//        return Response.ok(detailDemandes).build();
//
//    }


    @POST
    @Path("/send")
    public Response sendNotification() {

        Mail mail = Mail.withText("charle_andre_as@outlook.com", "Notification Subject", "Hey, This is the body of the notification.");

        mailer.send(mail);

        return Response.ok().build();

    }

    @POST
    @Path("/sessionOuverte")
    @RolesAllowed({"PRS", "CDG", "ACH"})
    public Response emailSessionOuverte(List<MyMail> listEmail) {

        System.out.println("I send mail");

        for (MyMail m : listEmail) {
            if (m.getEmail() != null) {

                Mail mail = Mail.withText(m.getEmail(), "Session Ouverte", "Hey " + m.getUsername() + ",\nUne session CD a été ouverte!");
                System.out.println(m.getEmail());
                mailer.send(mail);
            }


        }

        System.out.println("I sent mail");

        return Response.ok().build();

    }
//GET SESSION ACTIVE

    // @GET

    // @Path("/session/getByDir/{idDirection}")

    // @Produces(MediaType.APPLICATION_JSON)

    // public Response getSessionActive(@PathParam("idDirection") Integer idDirection) {

    //     // Récupérer les données depuis PostgreSQL

    //     SessionCd session = sessionCdService.getSessionActive(idDirection);

    //     return Response.ok(session).build();

    // }
    @GET
    @Path("/session/active")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"PRS", "CDG", "ACH"})
    public Response getSessionActive(
            @QueryParam("dir") @DefaultValue("") String idDirection
    ) {
        SessionCd session;

        if (!Objects.equals(idDirection, "")) {
            System.out.println("iciiiiiiiiiiii " + idDirection);
            // Récupérer les données depuis PostgreSQL
            session = sessionCdService.getActiveSession(Integer.valueOf(idDirection));
            return Response.ok(session).build();
        } else
            return null;
    }

    @PUT
    @Path("session/{id}")
    @RolesAllowed({"PRS", "CDG", "ACH"})
    public Response updateSession(@PathParam("id") Long id, SessionCd session) {
        session.setId(id); // Assure que l'ID de l'utilisateur est correctement défini
        sessionCdService.updateSessionCd(session);
        return Response.ok(session).build();
    }

    //GET SESSION ACTIVE
    @GET
    @Path("/checkSession/{idDirection}")
    @RolesAllowed({"PRS", "CDG", "ACH"})
    @Produces(MediaType.APPLICATION_JSON)
    public boolean checkSession(@PathParam("idDirection") Integer idDirection) {
        // Récupérer les données depuis PostgreSQL
        return sessionCdService.checkSession(idDirection);
    }


    // LIST DEMANDE ACTIVE
    @GET
    @Path("/active/avectitre")
    @RolesAllowed({"PRS", "ACH", "CDG"})
//    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllActive_dmdAvecTitre(
            @QueryParam("idDirection") @DefaultValue("") String idDirection,
            @QueryParam("idSession") @DefaultValue("") String idSession
    ) {

        List<Active> active_dmds = activeService.getActiveAvecTitre(idDirection, idSession);
        return Response.ok(active_dmds).build();
    }

    @GET
    @Path("/active/sanstitre")
    @RolesAllowed({"PRS", "ACH", "CDG"})
//    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllActive_dmd(
            @QueryParam("idDirection") @DefaultValue("") String idDirection,
            @QueryParam("idSession") @DefaultValue("") String idSession
    ) {

        List<Active> active_dmds = activeService.getActiveSansTitre(idDirection, idSession);
        return Response.ok(active_dmds).build();
    }

    // get titre by idDirection, idSession
    @GET
    @Path("/titre/get")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTitreBySession(
            @QueryParam("idSession") @DefaultValue("") String idSession,
            @QueryParam("idDirection") @DefaultValue("") String idDirection
    ) {
        // Récupérer les données depuis PostgreSQL

        List<TitreDepense> titre_dmds = titredemandeService.getTitres(idDirection, idSession);
        return Response.ok(titre_dmds).build();
    }

    //verifier existance coms cdg
    @GET
    @PermitAll
    @Path("checkAvisCdgByIdDemande/{idDemande}")
    public boolean checkAvisCdgByIdDemande(@PathParam("idDemande") Long idDemande) {
        return avisCdgService.checkAvisCdgByIdDemande(idDemande);
    }

    @GET
    @PermitAll               /*check avis achat*/
    @Path("/checkAvisAchatByIdDemande/{idDemande}")
    public boolean checkAvisAchatByIdDemande(@PathParam("idDemande") Long idDemande) {
        return avisAchatService.checkAvisAchatByIdDemande(idDemande);
    }

    @GET
    @PermitAll               /*get validation*/
    @Path("/getValidation")
    public Response getValidation(
            @QueryParam("montantMga") @DefaultValue("") String montantMga,
            @QueryParam("idDirection") @DefaultValue("") String idDirection


    ) {
        List<Active> listeValidation = detailDemandeService.getValidation(idDirection, montantMga);
        return Response.ok(listeValidation).build();
    }

    //valider demande
    //refuser demande

    //
    @DELETE
    @PermitAll               /* supprimer demande */
    @Path("/supprimerDemande/{idDemande}")
    public void supprimerDemande(
            @PathParam("idDemande") @DefaultValue("") Long id

    ) {
        System.out.println("#########################################");
        this.demandeService.delete(id);
    }

    @POST
    @Path("/demandeSoumise")
    @RolesAllowed({"PRS", "CDG", "ACH"})
    public Response notifierDemandeSoumise(List<MyMail> listEmail) {

        System.out.println("I send mail");

        for (MyMail m : listEmail) {
            if (m.getEmail() != null) {

                Mail mail = Mail.withText(m.getEmail(), "Session Ouverte", "Hey " + m.getUsername() + ",\nUne demande soumise!");
                System.out.println(m.getEmail());
                mailer.send(mail);
            }


        }

        System.out.println("I sent mail");

        return Response.ok().build();

    }

    //soumettre une demande (fonction maika)
    @POST
    @Path("/soumettre/{idDemande}")
    @RolesAllowed({"PRS", "CDG", "ACH"})
    public Response sousmettreDemande(

            @PathParam("idDemande") Long idDemande
//            @PathParam("idSession") Long idSession
    ) {
        System.out.println("ty id ---------------");
        System.out.println(idDemande);

        //getbyid demande


        Demande demande = demandeService.getDemandeById(Long.valueOf(idDemande));
//        // validation prs = true
        demande.setValidationPrescripteur(true);
//        System.out.println("montant !!!!!!!!!!!!!!");
        System.out.println(demande.getMontantHt());

        // est soumis = true
        demande.setEstSoumis(false);
        SessionCd c = sessionCdService.getidSession();
        System.out.println("IDDDDD SESSSIOSN");
        System.out.println(c.getId());


        //update SESSION
        demande.setIdSession(c.getId());
        //update

        demandeService.updateDemande(demande);


        return Response.ok().build();

    }

    @GET
    @PermitAll
    @Path("isExist/{idDemande}")
    public boolean isdemandeExist(@PathParam("idDemande") Long idDemande) {

        return activeService.estSoumis(idDemande);
    }


    // AUTHENTIFICATION LDAP
    @GET
    @PermitAll
    @Path("yes")
    public boolean authUser() {
        String username, password;
        username = "dre";
        password = "password";
        try {
            Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldap://localhost:10389");
            env.put(Context.SECURITY_PRINCIPAL, "uid=" + username + ",ou=users,ou=system");
            env.put(Context.SECURITY_CREDENTIALS, password);
            DirContext con = new InitialDirContext(env);
            con.close();
            return true;


        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;

        }
    }

    @POST
    @PermitAll
    @Path("/login")
    @Produces("text/plain")
    public Response authenticateUser() {
        // Votre logique d'authentification ici
        boolean isAuthenticated = true; // Exemple simpliste

        if (isAuthenticated) {
            // Construit l'URI de la page Angular où vous voulez rediriger, en incluant le port 4200
            String redirectUri = UriBuilder.fromUri("http://localhost:4200/main/MenuDemande").build().toString();

            // Retourne une réponse avec un code de statut 302 (Found) et l'en-tête Location contenant l'URI de redirection
            return Response.status(Response.Status.FOUND)
                    .location(UriBuilder.fromUri(redirectUri).build())
                    .build();
        } else {
            // Gérer le cas où l'utilisateur n'est pas authentifié
            return Response.status(Response.Status.UNAUTHORIZED).entity("Non autorisé").build();
        }
    }


//    @POST
//    @PermitAll
//    @Path("/realms")
//    public String getToken(
//           ) {
//        Client client = ResteasyClientBuilder.newClient();
//        URI target = UriBuilder.fromUri("http://localhost:8083")
//                .path("/realms/oma/protocol/openid-connect/token")
//                .queryParam("client_id", "angular-client")
//                .queryParam("client_secret", "F6ONL3ox63NBv1h1J5wmmibHlDhLA1MI")
//                .queryParam("grant_type", "password")
//                .queryParam("username", "charlesandrea")
//                .queryParam("password", "password")
//                .build("");
//
//        return client.target(target).request().post(null).toString();
//    }


    @Inject
    Keycloak keycloak;


    //    @POST
//    @PermitAll
//    @Path("/realms")
//    public String getTokenAdmin(
//    ) {
//        String adminToken = keycloak.tokenManager().getAccessToken().getToken();
//
//
//        return keycloak.realm("your-realm").clients().get("your-client-id").roles().get(roleId).users().list();
//
//        return  adminToken;
//    }
    public void initKeycloak() {
        keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8083")
                .realm("master")
                .clientId("angular-client")
                .grantType("password")
                .username("charlesandrea")
                .password("password")
                .clientSecret("F6ONL3ox63NBv1h1J5wmmibHlDhLA1MI")
                .build();
    }


    @GET
    @PermitAll
    @Path("/roles")
    public List<RoleRepresentation> getRoles() {
        return keycloak.realm("oma").roles().list();
    }

    @POST
    public void assignerRoles(

    ) {
         keycloak.realm("oma").roles().list();


    }


}


