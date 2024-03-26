package org.dre.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
@Entity

public class Demande extends PanacheEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @JsonProperty("id_titre_depense") // Annotation pour personnaliser le nom de la propriété dans JSON
    private Long id_titre_depense ;
    @JsonProperty("motif") // Annotation pour personnaliser le nom de la propriété dans JSON
    private String  motif ;
    @JsonProperty("id_fournisseur") // Annotation pour personnaliser le nom de la propriété dans JSON
    private Long id_fournisseur ;
    private  String coms_prescripteur;
    @JsonProperty("montant_ht") // Annotation pour personnaliser le nom de la propriété dans JSON
    private double montant_ht ;
//    @JsonProperty("id_rubrique") // Annotation pour personnaliser le nom de la propriété dans JSON
//    private Long id_rubrique ;
    @JsonProperty("is_regularisation") // Annotation pour personnaliser le nom de la propriété dans JSON
    private boolean is_regularisation ;
    @JsonProperty("id_periode") // Annotation pour personnaliser le nom de la propriété dans JSON
    private  Long id_periode ;
    @JsonProperty("is_deleted") // Annotation pour personnaliser le nom de la propriété dans JSON
    private boolean is_deleted;

    private  String nom_reference;
    private  Long id_reference;

    private Integer id_devise;
    private Integer id_direction;
    private Integer id_etat_final;
    private boolean is_valdby_ach;
    private boolean is_valdby_pres;
    private boolean is_valdby_cdg;

    public String getNom_reference() {
        return nom_reference;
    }

    public void setNom_reference(String nom_reference) {
        this.nom_reference = nom_reference;
    }

    public Long getId_reference() {
        return id_reference;
    }

    public void setId_reference(Long id_reference) {
        this.id_reference = id_reference;
    }

    public String getComs_prescripteur() {
        return coms_prescripteur;
    }

    public void setComs_prescripteur(String coms_prescripteur) {
        this.coms_prescripteur = coms_prescripteur;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public Integer getId_devise() {
        return id_devise;
    }

    public void setId_devise(Integer id_devise) {
        this.id_devise = id_devise;
    }

    public Integer getId_direction() {
        return id_direction;
    }

    public void setId_direction(Integer id_direction) {
        this.id_direction = id_direction;
    }

    public Integer getId_etat_final() {
        return id_etat_final;
    }

    public void setId_etat_final(Integer id_etat_final) {
        this.id_etat_final = id_etat_final;
    }

    public boolean isIs_valdby_ach() {
        return is_valdby_ach;
    }

    public void setIs_valdby_ach(boolean is_valdby_ach) {
        this.is_valdby_ach = is_valdby_ach;
    }

    public boolean isIs_valdby_pres() {
        return is_valdby_pres;
    }

    public void setIs_valdby_pres(boolean is_valdby_pres) {
        this.is_valdby_pres = is_valdby_pres;
    }

    public boolean isIs_valdby_cdg() {
        return is_valdby_cdg;
    }

    public void setIs_valdby_cdg(boolean is_valdby_cdg) {
        this.is_valdby_cdg = is_valdby_cdg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId_titre_depense() {
        return id_titre_depense;
    }

    public void setId_titre_depense(Long id_titre_depense) {
        this.id_titre_depense = id_titre_depense;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Long getId_fournisseur() {
        return id_fournisseur;
    }

    public void setId_fournisseur(Long id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    public double getMontant_ht() {
        return montant_ht;
    }

    public void setMontant_ht(double montant_ht) {
        this.montant_ht = montant_ht;
    }

//    public Long getId_rubrique() {
//        return id_rubrique;
//    }
//
//    public void setId_rubrique(Long id_rubrique) {
//        this.id_rubrique = id_rubrique;
//    }

    public boolean isIs_regularisation() {
        return is_regularisation;
    }

    public void setIs_regularisation(boolean is_regularisation) {
        this.is_regularisation = is_regularisation;
    }

    public Long getId_periode() {
        return id_periode;
    }

    public void setId_periode(Long id_periode) {
        this.id_periode = id_periode;
    }

    public boolean is_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}
