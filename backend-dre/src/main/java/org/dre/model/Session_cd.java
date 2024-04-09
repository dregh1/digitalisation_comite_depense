package org.dre.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import jakarta.persistence.*;

import java.sql.Timestamp;
@Entity
public class Session_cd extends PanacheEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id  ;
    @JsonProperty("ref")
    private  String ref ;

    @JsonProperty("date_cloture")
    private Timestamp date_cloture;
    @JsonProperty("taux_eur")
    private float taux_eur;

    @JsonProperty("taux_usd")
    private float taux_usd;

    @JsonProperty("taux_gbp")
    private float taux_gbp;

    @JsonProperty("taux_mga ")
    private float taux_mga;

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

    public Timestamp getDate_cloture() {
        return date_cloture;
    }

    public void setDate_cloture(Timestamp date_cloture) {
        this.date_cloture = date_cloture;
    }

    public float getTaux_eur() {
        return taux_eur;
    }

    public void setTaux_eur(float taux_eur) {
        this.taux_eur = taux_eur;
    }

    public float getTaux_usd() {
        return taux_usd;
    }

    public void setTaux_usd(float taux_usd) {
        this.taux_usd = taux_usd;
    }

    public float getTaux_gbp() {
        return taux_gbp;
    }

    public void setTaux_gbp(float taux_gbp) {
        this.taux_gbp = taux_gbp;
    }

    public float getTaux_mga() {
        return taux_mga;
    }

    public void setTaux_mga(float taux_mga) {
        this.taux_mga = taux_mga;
    }
}
