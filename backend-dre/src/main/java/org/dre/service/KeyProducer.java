package org.dre.service;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


@Singleton
public class KeyProducer {

    private static  SecretKey secretKey ;
    private static  SecretKey publicKey ;

    public static SecretKey getSecretKey() {
        return secretKey;
    }

    public static SecretKey getPublicKey() {
        return publicKey;
    }

    public void setSecretKey(SecretKey secretKey) {
        KeyProducer.secretKey = secretKey;
    }

    public void setPublicKey(SecretKey publicKey) {
        KeyProducer.publicKey = publicKey;
    }

    public KeyProducer(){
        this.setSecretKey(produceSecretKey());
        this.setPublicKey(produceSecretKey());
    }

    // Méthode pour produire la clé de signature
    @Produces
    private static SecretKey produceSecretKey() {

        //cle secrete en bean
        SecretKey secretKey ;

        // Générer une clé de signature, par exemple avec AES
        try {

        //vaovoa
            secretKey = JwtKeyGeneratorService.generateSecretKey( );
            return secretKey;

        //taloha
            //            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            //            keyGen.init(256); // Taille de la clé
            //            return keyGen.generateKey();
        } catch (Exception e) {
            // Gestion de l'erreur
            e.printStackTrace();
            return null;
        }
    }
}
