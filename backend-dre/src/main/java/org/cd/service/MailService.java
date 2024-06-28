package org.cd.service;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.cd.model.Demande;
import org.cd.model.MyMail;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MailService {
    @Inject
    Mailer mailer;

    //recuperation des mails des Controleur De Gestion & Achat
    public List<UserRepresentation> getMailCDGandACH() {
        List<UserRepresentation> listCDGandAchat = new ArrayList<>();


        // Configuration du client Keycloak
        String serverUrl = "http://localhost:8083";
        String realmName = "oma";
        String clientId = "angular-client";
        String clientSecret = "F6ONL3ox63NBv1h1J5wmmibHlDhLA1MI";

        try (Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realmName)
                .username("charlesandrea")
                .password("password")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build()) {

            // Obtention des memebre d' rôle par son nom
            String roleNamePrs = "CDG";
            String roleNameACH = "ACH";

            List<UserRepresentation> userMembersCDG = keycloak.realm(realmName).roles().get(roleNamePrs).getUserMembers();
            List<UserRepresentation> userMembersACH = keycloak.realm(realmName).roles().get(roleNameACH).getUserMembers();
            System.out.println(" CDG"+ userMembersCDG.get(0).getEmail() +" " + userMembersCDG.size());
            System.out.println(" ACH"+ userMembersACH.get(0).getEmail() +" " + userMembersACH.size());


            listCDGandAchat.addAll( userMembersACH);
            listCDGandAchat.addAll( userMembersCDG);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCDGandAchat;
    }

    public  void notificationDemandeSoumise (  Demande demande ){
        List<UserRepresentation> userMembersCDGandACH = this.getMailCDGandACH();

        String subject = "Demande soumise";

        System.out.println("I send mail");

        for (UserRepresentation m : userMembersCDGandACH){
            if(m.getEmail()!=null)
            {

                Mail mail = Mail.withText(m.getEmail(), subject, "Hey "+m.getUsername()+",\nLa demande ayant l'identifiant :"+ demande.getIdentifiant() +"a été soumise!");
                    System.out.println(m.getEmail())   ;
                mailer.send(mail);
            }


        }

        System.out.println("I sent mail")   ;
    }

    //notification des prescripteurs A L'OUVERTURE D'UNE SESSION
    public  void notificationSessionOuverte (  ){
        List<UserRepresentation> userMembersPRS = this.getMailPRS();

        String subject = "Session CD ouverte";

        System.out.println("I send mail");

        for (UserRepresentation m : userMembersPRS){
            if(m.getEmail()!=null)
            {
                Mail mail = Mail.withText(m.getEmail(), subject, "Hey "+m.getUsername()+",\nUne session CD a été ouverte! ");
                System.out.println(m.getEmail())   ;
                mailer.send(mail);
            }
        }
        System.out.println("I sent mail")   ;
    }

    //list des PRESCRIPTEURS
    private List<UserRepresentation> getMailPRS() {
        List<UserRepresentation> listPRS = new ArrayList<>();


        // Configuration du client Keycloak
        String serverUrl = "http://localhost:8083";
        String realmName = "oma";
        String clientId = "angular-client";
        String clientSecret = "F6ONL3ox63NBv1h1J5wmmibHlDhLA1MI";

        try (Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realmName)
                .username("charlesandrea")
                .password("password")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build()) {

            // Obtention des memebre d' rôle par son nom
            String roleNamePrs = "PRS";

            List<UserRepresentation> userMembersACH = keycloak.realm(realmName).roles().get(roleNamePrs).getUserMembers();
            System.out.println(" ACH"+ userMembersACH.get(0).getEmail() +" " + userMembersACH.size());


            listPRS.addAll( userMembersACH);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPRS;
    }

}
