package org.dre.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Avis_cdg extends PanacheEntity {
    private Long id;
    private  Long id_demande;
    private  String commentaire;
    private  Long id_rubrique;
    private  String sousrubrique;
    private  double montant_budget_mensuel;
    private  double montant_engage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_demande() {
        return id_demande;
    }

    public void setId_demande(Long id_demande) {
        this.id_demande = id_demande;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Long getId_rubrique() {
        return id_rubrique;
    }

    public void setId_rubrique(Long id_rubrique) {
        this.id_rubrique = id_rubrique;
    }

    public String getSousrubrique() {
        return sousrubrique;
    }

    public void setSousrubrique(String sousrubrique) {
        this.sousrubrique = sousrubrique;
    }

    public double getMontant_budget_mensuel() {
        return montant_budget_mensuel;
    }

    public void setMontant_budget_mensuel(double montant_budget_mensuel) {
        this.montant_budget_mensuel = montant_budget_mensuel;
    }

    public double getMontant_engage() {
        return montant_engage;
    }

    public void setMontant_engage(double montant_engage) {
        this.montant_engage = montant_engage;
    }
}
