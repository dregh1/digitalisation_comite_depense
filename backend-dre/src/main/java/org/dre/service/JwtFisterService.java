package org.dre.service;


import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import jakarta.ws.rs.ext.Provider;

import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;


public class JwtFisterService {
    @Inject
    private JwtService jwtService;


//    @Override
//    public void filter(ContainerRequestContext requestContext) {
//        // Récupérer le token JWT de l'en-tête Authorization de la requête
//        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            String token = authorizationHeader.substring("Bearer".length()).trim();
//
//            // Valider le token JWT
//            boolean validToken = jwtService.verifyToken(token);
//            if (!validToken) {
//                // Si le token n'est pas valide, renvoyer une réponse d'erreur 401 Unauthorized
//                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
//            }
//        } else {
//            // Si l'en-tête Authorization est manquant ou mal formé, renvoyer une réponse d'erreur 401 Unauthorized
//            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
//        }
//    }
}
