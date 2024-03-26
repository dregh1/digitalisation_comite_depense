package org.dre.service;


import io.smallrye.jwt.auth.principal.*;
import io.smallrye.jwt.build.Jwt;

import io.smallrye.jwt.build.JwtClaimsBuilder;
import io.smallrye.jwt.build.JwtSignature;
import io.smallrye.jwt.build.JwtSignatureException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.Claims;
import io.smallrye.jwt.auth.principal.JWTParser;


import javax.crypto.SecretKey;
import io.smallrye.jwt.auth.principal.JWTParser;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped

public class JwtService {
    @Inject
    private KeyProducer keyProducer;
    @Inject
    private JsonWebToken jsonWebToken;


    public String generateToken(String username, SecretKey signingKey/*String signingKey*/) {

        Set<String> groups = new HashSet<>();
        groups.add("user"); // Ajoutez les rôles ou groupes nécessaires

        return Jwt.issuer("https://votre-application.com/issuer") // Issuer
                .upn(username) // Subject
                .groups(groups) // Roles or groups
                .claim(Claims.birthdate.name(), "2000-01-01") // Claims
                .expiresIn(3600)
                .sign(signingKey);
    }

    /////OFFICIEL
    public String genToken()
    {
        String token =
                Jwt.issuer("https://example.com/issuer")
                        .upn("jdoe@quarkus.io")
                        .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                        .claim(Claims.birthdate.name(), "2001-07-13")
                        .sign();
        System.out.println(token);
        return token;

    }
    ///////////////////////////////////////////////////////////////////////////////////////////

    // GENERER TOKEN
    public static String genererToken(String username, PublicKey publicKey, String privateKey) {
        Set<String> groups = new HashSet<>();
        groups.add("user"); // Ajoutez les rôles ou groupes nécessaires

        // Générer le token JWT
        String jwtToken = Jwt.issuer("votre-application") // Issuer
                .upn(username) // Subject
                .groups(groups) // Roles or groups
                .claim(Claims.birthdate.name(), "2000-01-01") // Claims
                .expiresIn(3600)
                .sign(privateKey); // Signer avec la clé privée

        return jwtToken;
    }

    // VERIFIER TOKEN
    public boolean verifierToken(String jwtToken) {
        jsonWebToken = decodeJwt(jwtToken);

        if (jsonWebToken == null /*|| !jsonWebToken.isActive()*/) {
            return false; // Le token n'est pas valide
        }

        // Extraire et vérifier les informations du token
        String username = jsonWebToken.getName();
        Set<String> groups = jsonWebToken.getGroups();
        String birthdate = jsonWebToken.getClaim(Claims.birthdate.name());

        // Afficher les informations du token
        System.out.println("Nom d'utilisateur: " + username);
        System.out.println("Groupes: " + groups);
        System.out.println("Date de naissance: " + birthdate);

        // Ajoutez des vérifications supplémentaires selon vos besoins

        return true; // Le token est vérifié avec succès
    }

    // decodage
    private static final Logger LOG = Logger.getLogger(JwtService.class.getName());

    public JsonWebToken decodeJwt(String jwtToken) {
        try {
            // Créer un parseur JWT
//            JWTParser parser = JWTParser.builder().build();;
            JWTParser parser ;//= JWTParser.builder().build();;
            parser = new DefaultJWTParser();
            // Déchiffrer le token JWT
            return parser.parse(jwtToken);
        } catch (ParseException e) {
            LOG.log(Level.SEVERE, "Erreur lors du décodage du token JWT", e);
            return null;
        }
    }

    public boolean verifyToken(String jwtToken) {
        JsonWebToken decodedToken = decodeJwt(jwtToken);

        if (decodedToken == null /*|| !decodedToken.isActive()*/) {
            return false; // Le token n'est pas valide
        }

        // Extraire et vérifier les informations du token
        String username = decodedToken.getName();
        Set<String> groups = decodedToken.getGroups();
        String birthdate = decodedToken.getClaim("birthdate");

        // Afficher les informations du token
        System.out.println("Nom d'utilisateur: " + username);
        System.out.println("Groupes: " + groups);
        System.out.println("Date de naissance: " + birthdate);

        // Ajoutez des vérifications supplémentaires selon vos besoins

        return true; // Le token est vérifié avec succès
    }



//    public static void main(String[] args) throws Exception {
//        // Charger les clés RSA à partir des fichiers
//        RSAPublicKey publicKey = (RSAPublicKey) JwtKeyLoader.loadPublicKey("src/main/resources/jwt/publicKey.pem");
//        RSAPrivateKey privateKey = (RSAPrivateKey) JwtKeyLoader.loadPrivateKey("src/main/resources/jwt/privateKey.pem");
//
//        // Générer le token JWT pour un utilisateur donné
//        String jwtToken = JwtService.genererToken("utilisateur", publicKey, privateKey);
//
//        // Imprimer le token JWT généré
//        System.out.println("Token JWT généré :\n" + jwtToken);
//    }

    ///////////////////////////////////////////////////////////////////////////////////////////
//    // Clé secrète pour signer le token JWT (à remplacer par votre propre clé secrète)
//    private static final String SECRET_KEY = "A2Z3E4R5T6";
//
//    // Durée de validité du token JWT (en millisecondes)
//    private  static final long EXPIRATION_TIME = 86400000; // 24 heures


    public void decodeToken(){
        // Remplacer "votre_token" par le token que vous voulez décoder
        String token = "votre_token";

        // Créer un parser JWT
//        JWTParser parser = JWTParser.instance();

        // Décoder le token et récupérer les claims
        try {
//            Claims claims = parser.parse(token);
//            System.out.println("Subject : " + claims.subject());
//            System.out.println("Issuer : " + claims.issuer());
            // Accéder aux autres claims en utilisant la méthode get(String claimName)
        } catch (Exception e) {
            System.err.println("Erreur lors du décodage du token : " + e.getMessage());
        }
    }


    public boolean validateToken(String token) {
        try {
            // Parse the token
            JwtClaimsBuilder claimsBuilder = Jwt.claims(token);

            // Validate signature
            SecretKey secretKey = keyProducer.getPublicKey();
            claimsBuilder.sign(secretKey);

            // Extract claims for further validation if needed
//            String username = claimsBuilder.getSubject();
//            Set<String> groups = claimsBuilder.getGroups();
//            Instant expiration = claimsBuilder.getExpirationTime();

            // Validate claims as needed (e.g., check expiration, authorized groups)
            // ...

            return true;
        } catch (JwtSignatureException e) {
            // Handle invalid signature
            System.out.println("ERREUR1");
            e.printStackTrace();
            // ...
            return false;
        } catch (Exception e) {
            System.out.println("ERREUR2");

            // Handle other errors
            e.printStackTrace();

            // ...
            return false;
        }
    }

}

