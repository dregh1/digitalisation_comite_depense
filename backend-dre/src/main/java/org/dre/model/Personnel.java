package org.dre.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Calendar;
import java.util.Date;

@Entity

public class Personnel   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("nom") // Annotation pour personnaliser le nom de la propriété dans JSON
    private String nom;

    @JsonProperty("prenom") // Annotation pour personnaliser le nom de la propriété dans JSON
    private String prenom;

    @JsonProperty("age") // Annotation pour personnaliser le nom de la propriété dans JSON
    private int age;

    public Long getId() {
        return id;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        Date date = new Date();
// Créer un objet Calendar et l'initialiser avec l'heure actuelle
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Décrémenter de 30 minutes
        calendar.add(Calendar.MINUTE, -30);

        // Obtenir l'heure moins 30 minutes
        Date heureMoins30Minutes = calendar.getTime();

        System.out.println("ty le daty :: "+heureMoins30Minutes);
    }
}
