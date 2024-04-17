package org.dre.controller;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.dre.model.*;
import org.dre.repository.ActiveRepository;
import org.dre.repository.BrouillonRepository;
import org.dre.repository.DetailDemandeRepository;
import org.dre.service.*;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import java.util.List;

@Path("/prescripteur")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@Authenticated
public class PrescripteurCnt {
    @Inject
    PeriodeService periodeService;
    @Inject
    ActiveService activeService ;
    @Inject
    AvisAchatService avis_achatService;

    @Inject
    RubriqueService rubriqueService;
    @Inject
    DetailDemandeService detailDemandeService;



    @Inject
    DemandeService demandeService;

    @Inject
    BrouillonService brouillonService;
    @Inject
    FournisseurService fournisseurService;
    @Inject
    TitreDemandeService titredemadeService;

    @Inject
    BrouillonRepository brouillonRepository;

    @Inject
    ActiveRepository activeRepository;
    @POST
    @Path("demande/create")
    public Response createDemande(Demande demande) {
        demandeService.create(demande);
        return Response.status(Response.Status.CREATED).entity(demande).build();
    }

    //validation prescripteur
    @PUT
    @Path("demande/{id}")
    public Response updateDemande(@PathParam("id") Long id, Demande demande) {
        demande.setId(id); // Assure que l'ID de l'utilisateur est correctement défini
        demandeService.updateDemande(demande);
        return Response.ok(demande).build();
    }
    @GET
    @Path("/periode/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllperiode() {
        // Récupérer les données depuis PostgreSQL
        List<Periode> sessionCds = periodeService.getAll ();
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
    @Path("/avis_achat/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvisAchat() {
        // Récupérer les données depuis PostgreSQL
        List<AvisAchat> avis_achat = avis_achatService.getAll ();
        return Response.ok(avis_achat).build();
    }


    @GET
    @Path("/titre/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTitre() {
        // Récupérer les données depuis PostgreSQL
        List<TitreDepense> titre_dmds = titredemadeService.getAll ();
        return Response.ok(titre_dmds).build();
    }

    //TITRE par id session
    @GET
    @Path("/titre/getBySession/{idSession}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTitreBySession(@PathParam("idSession") Integer idSession) {
        // Récupérer les données depuis PostgreSQL

        List<TitreDepense> titre_dmds = titredemadeService.getAllByIdSession (idSession);
        return Response.ok(titre_dmds).build();
    }

    @POST
    @Path("/titre/create")
    public Response creatTitre(TitreDepense titre_Dmd) {
        // Récupérer les données depuis PostgreSQL

        titredemadeService.create(titre_Dmd);

        return Response.status(Response.Status.CREATED).entity(titre_Dmd).build();

    }
    @GET
    @Path("/idtitre/next")
    public void getNextVal() {
       Long id =  titredemadeService.getNextSequenceValue("titre_depense_seq");
        System.out.println(id);
    }

    //rubrique et sous rubrique
    @GET
    @Path("/rubrique/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRubrique() {
        // Récupérer les données depuis PostgreSQL
        List<Rubrique> rubriques = rubriqueService.getAll ();
        return Response.ok(rubriques).build();
    }


    //select BROUILLON
    @GET
    @Path("/brouillon/get")
    @Produces(MediaType.APPLICATION_JSON)
//    @SecurityRequirement(name = "Keycloak")
    public Response getAllBrouillon() {
        // Récupérer les données depuis PostgreSQL

            // mila misy filtre


        List<Brouillon> brouillons = brouillonService.getAll ();
        return Response.ok(brouillons).build();
    }
    @GET
    @Path("/brouillon/getByIdDir/{id}")
    @Produces(MediaType.APPLICATION_JSON)
//    @SecurityRequirement(name = "Keycloak")
    public Response getBrouillonsByIdDirection(@PathParam("id") Integer id) {
        // Récupérer les données depuis PostgreSQL

        // mila misy filtre

        List<Brouillon> brouillons = brouillonService.getAllByIdDir (id);

        return Response.ok(brouillons).build();
    }

    // select brouillon by id
    @GET
    @Path("brouillon/{id}")
    public Brouillon getBrouillonById(@PathParam("id") Long id) {
        return brouillonRepository.findById(id);
    }


// LIST DEMANDE ACTIVE
    @GET
    @Path("/active_dmd/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllActive_dmd() {
        // Récupérer les données depuis PostgreSQL
        List<Active> active_dmds = activeService.getAll ();
        return Response.ok(active_dmds).build();
    }

    @GET
    @Path("/active/getByIdDir/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActiveByIdDirection(@PathParam("id") Integer id) {
        // Récupérer les données depuis PostgreSQL

        // mila misy filtre

        List<Active> actives = activeService.getAllByIdDir (id);

        return Response.ok(actives).build();
    }
//GET DEMANDE ACTIVE BY ID
    @GET
    @Path("/active_dmd/{id}")
    public Active getActiveDmdById(@PathParam("id") Long id) {
        return activeRepository.findById(id);
    }

    @GET
    @Path("/detailDemande/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDetailDemande() {
        List<DetailDemande> detailDemande = detailDemandeService.getAll ();
        return Response.ok(detailDemande).build();
    }
}
