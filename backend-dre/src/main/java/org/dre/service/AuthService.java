package org.dre.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.dre.model.Login;

import java.util.List;

@ApplicationScoped
public class AuthService {
    @Inject
    private JwtService jwtService;

//    public Response login(Login login) {
//        // Logique de vérification des informations d'identification
//        if (credentialsAreValid(login.getUsername(), login.getPassword())) {
//            // Générer et renvoyer le token JWT en réponse à la connexion réussie
//            String token = jwtService.generateToken(login.getUsername());
//            return Response.ok().entity(token).build();
//        } else {
//            // Renvoyer une réponse d'erreur en cas d'échec de l'authentification
//            return Response.status(Response.Status.UNAUTHORIZED).entity("Identifiants incorrects").build();
//        }
//    }

    private boolean credentialsAreValid(String username, String password) {
        // Votre logique de validation des informations d'identification




        return true; // Exemple de validation factice
    }

    public boolean isUser(Login logReq)
    {

        List<Login> listLog  = Login.listAll();
        for ( Login p : listLog)
        {
            if(logReq.getUsername() != null && p.getUsername().equals(logReq.getUsername() ) &&  p.getPassword().equals(logReq.getPassword()) )
                return true;
        }
        return false;

    }
}
