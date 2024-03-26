package org.dre.service;

import jakarta.enterprise.context.ApplicationScoped;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class JwtKeyGeneratorService {

    /**
     * Generates a secret key for JWT signing using HMACSHA256 algorithm.
     *
     * @return SecretKey generated secret key
     * @throws NoSuchAlgorithmException if the algorithm is not available
     */

    public static SecretKey generateSecretKey() throws NoSuchAlgorithmException {

    // Génération d'une clé secrète aléatoire de 256 bits
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        SecureRandom random = new SecureRandom();
        keyGen.init(256, random);

        return keyGen.generateKey();
    }
}