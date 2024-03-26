package org.dre.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;

import jakarta.ws.rs.Path;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import org.dre.model.Login;
import org.dre.model.Personnel;
import org.dre.service.*;
import org.eclipse.microprofile.jwt.JsonWebToken;


import java.io.IOException;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Path("/authent")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginCnt {
    @Inject
    private JwtService jwtService;
    @Inject
    private AuthService authService;
    @Inject
    private KeyProducer keyProducer;
    @Inject
    private JwtKeyLoader jwtKeyLoader;
    @Inject
    JsonWebToken jwt;

@GET
@Path("/token")
@Produces(MediaType.APPLICATION_JSON)
public Response getToken() throws GeneralSecurityException, IOException {
    // Récupérer les données depuis PostgreSQL
//    String token = jwtService.genToken();
//    String token = jwtService.generateToken("randria",jwtKeyLoader.getSigningKey()); //tsy mandeha
//    String token = jwtService.generateToken("randria",keyProducer.getSecretKey()); //mandeha

    String token = jwtService.genererToken("randria",jwtKeyLoader.getPublicKey(),jwtKeyLoader.getSigningKey());

    //    String token = jwtService.genererToken("randria",JwtKeyLoader.loadPublicKey("/publicKey.pem") ,JwtKeyLoader.loadPrivateKey("/privateKey.pem"));
    return Response.ok(token).build();
}
    @POST
    @Path("/v")
    public Response verifUser(Personnel personnel) throws IOException {

    Login l = new Login();
    l.setPassword(personnel.getPrenom());
    l.setUsername(personnel.getNom());
        if(authService.isUser(l))
        {


//            String jwtToken = jwtService.generateToken(login.getUsername());
//        System.out.println(" TOKEN \n\n\n\n\n\n\n\n"+ jwtToken+"\n\n\n\n\n\n\n\n");
//            // Renvoyer le jeton JWT en réponse à la demande de connexion réussie
//            return Response.ok(new AuthResponse(jwtToken)).build();
            //response.sendRedirect("/accueil");
            return Response.ok("Authentification réussie").build();


        }

        // Authentification réussie, vous pouvez effectuer d'autres opérations et retourner une réponse appropriée
        // ...
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity("Identifiants incorrects")
                .build();


    }

@POST
@Path("/check")

public Response checkUser(Login login) {


    if(authService.isUser(login))
    {


//            String jwtToken = jwtService.generateToken(login.getUsername());
//        System.out.println(" TOKEN \n\n\n\n\n\n\n\n"+ jwtToken+"\n\n\n\n\n\n\n\n");
//            // Renvoyer le jeton JWT en réponse à la demande de connexion réussie
//            return Response.ok(new AuthResponse(jwtToken)).build();
        return Response.ok("{\"message\": \"Authentification réussie\"}").header("Content-Type", "application/json").build();
//        return Response.status(302).location(URI.create("/accueil")).build();

    }

    // Authentification réussie, vous pouvez effectuer d'autres opérations et retourner une réponse appropriée
    // ...
    return Response
            .ok("{\"message\":\"Identifiants incorrects\"}")
            .header("Content-Type", "application/json")
            .build();

}

    @GET
    @Path("/veriftoken")
    @Produces(MediaType.APPLICATION_JSON)
//    public String testToken(@QueryParam("token") String token) {
    public String testToken() {
//        JwtToken token =tokenManager.getAuthToken()
//        String authorizationHeader = request.getHeader("Authorization");
//        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
//            return "Missing or invalid authorization header";
//        }
        return "";
//    String token = securityContext.getToken();
//        System.out.println("\n\n\n\n\n\n\n"+token+"\n\n\n\n\n\n\n");

//        boolean isValid  =  jwtService.validateToken(token);
//        if (token == null || token.isEmpty()) {
//            return "Token manquant";
//        }

//        // Valider le token
//        boolean isValid = tokenValidator.validateToken(token);
//
//        // Répondre en fonction de la validation du token
//        if (isValid) {
//            return "Token valide";
//        } else {
//            return "Token invalide";
//        }

}

}
/*
package org.dre.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.Response;
import org.dre.model.Login;
import org.dre.service.AuthService;
//import io.jsonwebtoken;


@Path("/log")
public class AuthCnt {
    @Inject
    AuthService authService;


    // get email mdp front
    @POST
    @Path("/check")
    public Response checkUser(Login login) {
        if(authService.isUser(login))
        {


//            String jwtToken = generateJWTToken(loginRequest.getUsername());
//
//            // Renvoyer le jeton JWT en réponse à la demande de connexion réussie
//            return Response.ok(new AuthResponse(jwtToken)).build();
            return Response.ok("Authentification réussie").build();


        }

        // Authentification réussie, vous pouvez effectuer d'autres opérations et retourner une réponse appropriée
        // ...
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity("Identifiants incorrects")
                .build();

    }


    // get ny rehetra any @ base


}


* */