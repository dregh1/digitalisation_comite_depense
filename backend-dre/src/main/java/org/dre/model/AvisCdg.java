package org.dre.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class AvisCdg extends PanacheEntity {
    private Long id;
    private  Long idDemande;
    private  String commentaire;
    private  Long idRubrique;
    private  String sousRubrique;
    private  double montantBudgetMensuel;
    private  double montantEngage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_demande() {
        return idDemande;
    }

    public void setId_demande(Long idDemande) {
        this.idDemande = idDemande;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Long getId_rubrique() {
        return idRubrique;
    }

    public void setId_rubrique(Long idRubrique) {
        this.idRubrique = idRubrique;
    }

    public String getSousrubrique() {
        return sousRubrique;
    }

    public void setSousrubrique(String sousRubrique) {
        this.sousRubrique = sousRubrique;
    }

    public double getMontantBudgetMensuel() {
        return montantBudgetMensuel;
    }

    public void setMontantBudgetMensuel(double montantBudgetMensuel) {
        this.montantBudgetMensuel = montantBudgetMensuel;
    }

    public double getMontantEngage() {
        return montantEngage;
    }

    public void setMontantEngage(double montantEngage) {
        this.montantEngage = montantEngage;
    }
}
