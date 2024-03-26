package org.dre.service;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.ResultSet;
import java.util.Base64;

@ApplicationScoped
public class JwtKeyLoader {

    @Inject
    @ConfigProperty(name = "mp.jwt.sign.key.location")
    private String signingKey;

    @Resource(name = "publicKey.pem")
    private PublicKey publicKey;

    @Resource(name = "privateKey.pem")
    private PrivateKey privateKey;

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public String getSigningKey() {
        return signingKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    //    @ConfigProperty(name = "smallrye.jwt.sign.key.location")
    private String keyFileLocation;

    public String loadJwtSigningKey() throws IOException {
        return new String(Files.readAllBytes(Paths.get(keyFileLocation)));
    }


    //PUBLIC KEY
    public static PublicKey loadPublicKey(String filePath) throws IOException, GeneralSecurityException {
        byte[] keyBytes = Files.readAllBytes(Paths.get(filePath));
        String publicKeyContent = new String(keyBytes);
        System.out.println("\n\n\n CLE PUBLIC :\n\n\n "+publicKeyContent+"\n\n\n\n\n\n");
        publicKeyContent = publicKeyContent
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] decodedBytes = Base64.getDecoder().decode(publicKeyContent);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    //PRIVATE KEY
    public static PrivateKey loadPrivateKey(String filePath) throws IOException, GeneralSecurityException {
        byte[] keyBytes = Files.readAllBytes(Paths.get(filePath));
        String privateKeyContent = new String(keyBytes);
        System.out.println("\n\n\n CLE PRIVE :\n\n\n "+privateKeyContent+"\n\n\n\n\n\n");

        privateKeyContent = privateKeyContent
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] decodedBytes = Base64.getDecoder().decode(privateKeyContent);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    public static void main(String[] args) {
//        try {
//            PrivateKey pk =  JwtKeyLoader.loadPrivateKey("src/main/resources/privateKey.pem");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (GeneralSecurityException e) {
//            e.printStackTrace();
//        }


    }
}