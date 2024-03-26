package org.dre.controller;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.bouncycastle.util.encoders.Base64;



//reto le rectictification
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import org.bouncycastle.util.encoders.Base64;
import java.security.spec.PKCS8EncodedKeySpec;

import java.util.Date;

@Path("/tk")
public class tokenTestCnt {
    @GET
    @Path("/gen")
    public void testGenTkn() throws NoSuchAlgorithmException {


    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
//         Clé privée generé par
//        openssl genrsa -out rsaPrivateKey.pem 2048
//        openssl rsa -pubout -in rsaPrivateKey.pem -out publicKey.pem
//        openssl pkcs8 -topk8 -nocrypt -inform pem -in rsaPrivateKey.pem -outform pem -out privateKey.pem


        String privateKey = "-----BEGIN PRIVATE KEY-----\n" +
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCeqtD+PkQfPvkn\n" +
                "utPYsKwcQxwc/DPfH+iJVUZQOaKwFc9wwV7c9iluE77QkFbHZKru2t+U7fm7o5gO\n" +
                "RKY1tk3O+pGPAJs4clEhJNhm6O9W64RBNLG+NZRSZ+4WGGe0W3j1FrH42DZ/u0Ro\n" +
                "r6DBddxDy+reXWicyZFm//eoIlK/TkE2ZEHb9ISCIUsXSnaH6YoeyQjmr9BWVQiv\n" +
                "eixp6Yuk5qfOSyaILNfMR2RBVwMneJt/X8FlOGeEJ1vIMCkfTFqEGKNNM06f0MJM\n" +
                "spKbgRxlMQtaQwKt1u+uNzondqoOg/KSmVwWWuglUdkmPul22ekijHVjiLhZCgYk\n" +
                "JTaj8i1rAgMBAAECggEAJZTqKb5P3mLJGyr6cLHwIkWXDBqEos3pA1ZqcCgmq7SO\n" +
                "zPNQZ9/6kjMlGDuIAnSKtEatfJYdd99Y7dA+2iwTI/pfrdKD8YvKQaJ7i2yS/DCO\n" +
                "w+8aRqZkOlyZnSwsrCCadtroTrvPtNODcZRf7FLhWEGdoQvY8oIf6ZipbW6r0Nn1\n" +
                "td108wJ1QAgRjviNvmu2fpsyTCE9/8MHDQaLdbanQD0NSWb+L/OOKVvB7Kn6y9Pp\n" +
                "7sibJ5mTwTlT+fQITE0IefMRdORbQF0O0tgUpdCgEjku06U4MjNl8e3jZ3WxbExa\n" +
                "+zNujSJNRGGMZSOzJNTJQBZwZn+j4c+Zb9jmlmPjGQKBgQDRnb6KvVqbeYbHoEuE\n" +
                "cF9De1LWyTJt+4s7dN10ggUge6zI6JZ0LBckN2BbTyM/JWlIoQGgB+QJ4CjO43Q3\n" +
                "8DSegwUuXYJhUt/4187XhF4kT2bfHRIHs5P11e8lVOPln5L8wbqFXOuNezX5wQQO\n" +
                "/BlOP24VMVPyKf4EhKT8ako2VwKBgQDBxu/IS9CK+T6cvvJ3WE/UtXnGqqp8m/q4\n" +
                "EbxsgHAvnIfeGSOCzHaSVfz21NDO4RTRwqtYZNI5aWXqsYLLXPNXSjlZEOiNCxnM\n" +
                "cXX8upjvj7mQnC65QFucsc8s7Xg3uggLPvIKBUQbPihT2SG3KX/GZO37L4zh/21z\n" +
                "krKioQ0NDQKBgQCZYzosYjWscbhb2gOIzYYYZmUPATmi26BeCL2I5SAFThr5BaIb\n" +
                "Lqxcdx1Ok9FyAtlRKWh8B2oqg9q40h6YVHDc0Xl4HTssJq34dUtJz/Xn0iav+6DW\n" +
                "++S4zyzpf34o6KVxyf3IW1+PL2sm8MQchwOpi6btYSRRn8Le4YWto8XcjwKBgEy7\n" +
                "GLHoi4CsZ4Wr+tkVSC9DWSA47l9/aE1BAKq6RTIlOovQyoX9rqoNkKiw5qo06IiT\n" +
                "HERZUAFZ4fiIJ/GdMeKLyp+hGWTYU3PoLb9QqEScVe+UWNAUUvJ+WjpBIkx2xr6l\n" +
                "QzSCl1BuUYu6PCaKMUV/K9k+H/xRp6aPHVtWiJN1AoGACXQWlPc+mxf/XdIWI+jC\n" +
                "BL/ls3223B3QL2mcVY/B+hRhgxQdAaUZhgQdw2x2LU0EQrxz71O2Wt77rEZE+fIp\n" +
                "RnM1+8qqK4Mqj00CTdV/j/G7WsiJo2vqCWJ3zB+MM7Oc89BUrSTpHLYgeNiHMZxr\n" +
                "A7EInVJKU4aVQeX5fEPRr5o=\n" +
                "-----END PRIVATE KEY-----";

// Informations du token
        String subject = "1234567890"; // Identifiant unique de l'utilisateur
        String issuer = "votre-application"; // Nom de votre application
        long expirationTime = System.currentTimeMillis() + 3600000; // 1 heure en millisecondes

// Génération du token
        String token = Jwts.builder()
                .setSubject(subject)
                .setIssuer(issuer)
                .setExpiration(new Date(expirationTime))
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();

// Affichage du token
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n Token généré :\n\n\n\n\n\n\n\n\n\n\n\n\n " + token);

    }
}
