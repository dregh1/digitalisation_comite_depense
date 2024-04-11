package org.dre.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import jakarta.persistence.*;

import java.sql.Timestamp;
@Entity
public class SessionCd extends PanacheEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id  ;
    @JsonProperty("ref")
    private  String ref ;

    @JsonProperty("date_cloture")
    private Timestamp dateCloture;
    @JsonProperty("tauxEur")
    private float tauxEur;

    @JsonProperty("tauxUsd")
    private float tauxUsd;


    @JsonProperty("tauxMga ")
    private float tauxMga;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Timestamp getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(Timestamp dateCloture) {
        this.dateCloture = dateCloture;
    }

    public float getTauxEur() {
        return tauxEur;
    }

    public void setTauxEur(float tauxEur) {
        this.tauxEur = tauxEur;
    }

    public float getTauxUsd() {
        return tauxUsd;
    }

    public void setTauxUsd(float taux_usd) {
        this.tauxUsd = tauxUsd;
    }



    public float getTauxMga() {
        return tauxMga;
    }

    public void setTauxMga(float tauxMga) {
        this.tauxMga = tauxMga;
    }
}