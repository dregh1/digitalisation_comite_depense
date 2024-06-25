package org.oma.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BudgetMensuel {
    @Id
    private Integer id ;
    private int annee  ;
    private int numDuMois ;
    private double montant;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getNumDuMois() {
        return numDuMois;
    }

    public void setNumDuMois(int numDuMois) {
        this.numDuMois = numDuMois;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
