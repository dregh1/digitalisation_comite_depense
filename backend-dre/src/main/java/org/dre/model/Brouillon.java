package org.dre.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;

@Entity
public class Brouillon extends PanacheEntity {
    private long id;
    @Nullable
    private Long id_titre;
    private String titre;
    private String motif;
    private double montant_ht;
    private boolean is_regularisation;
    private String coms_prescripteur;

    private long id_periode;
    private String periode;
    private long id_direction;
    private String direction;
    private long id_devise;
    private String devise;
    private long id_fournisseur;
    private String fournisseur;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getId_titre() {
        return id_titre;
    }

    public void setId_titre(Long id_titre) {
        this.id_titre = id_titre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public double getMontant_ht() {
        return montant_ht;
    }

    public void setMontant_ht(double montant_ht) {
        this.montant_ht = montant_ht;
    }

    public boolean isIs_regularisation() {
        return is_regularisation;
    }

    public void setIs_regularisation(boolean is_regularisation) {
        this.is_regularisation = is_regularisation;
    }

    public String getComs_prescripteur() {
        return coms_prescripteur;
    }

    public void setComs_prescripteur(String coms_prescripteur) {
        this.coms_prescripteur = coms_prescripteur;
    }



    public long getId_periode() {
        return id_periode;
    }

    public void setId_periode(long id_periode) {
        this.id_periode = id_periode;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public long getId_direction() {
        return id_direction;
    }

    public void setId_direction(long id_direction) {
        this.id_direction = id_direction;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public long getId_devise() {
        return id_devise;
    }

    public void setId_devise(long id_devise) {
        this.id_devise = id_devise;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public long getId_fournisseur() {
        return id_fournisseur;
    }

    public void setId_fournisseur(long id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }
}
