package org.dre.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sousrubrique extends PanacheEntity{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("designation") // Annotation pour personnaliser le designation de la propriété dans JSON
    private String designation;

    @JsonProperty("id_rubrique") // Annotation pour personnaliser le designation de la propriété dans JSON
    private Long id_rubrique;



}
