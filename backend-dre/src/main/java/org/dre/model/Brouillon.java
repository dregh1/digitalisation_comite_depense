package org.dre.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;

@Entity
public class Brouillon extends PanacheEntity {
 private   Long id ;
 private  Integer id_titre ;
 private    String  titre ;
 private  String motif ;
 private  double montant_ht ;
 private  String  type_reference ;
 private  String  reference ;
 private  boolean is_regularisation ;
 private  String  coms_prescripteur ;
 private  Integer id_periode ;
 private  String  periode ;
 private  Integer id_direction ;
 private  String  devise ;
 private  Integer id_fournisseur ;

 private Integer id_rubrique;
 private String nomRubrique;
 private  String  fournisseur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getId_titre() {
        return id_titre;
    }

    public void setId_titre(Integer id_titre) {
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

    public String getType_reference() {
        return type_reference;
    }

    public void setType_reference(String type_reference) {
        this.type_reference = type_reference;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
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

    public Integer getId_periode() {
        return id_periode;
    }

    public void setId_periode(Integer id_periode) {
        this.id_periode = id_periode;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public Integer getId_direction() {
        return id_direction;
    }

    public void setId_direction(Integer id_direction) {
        this.id_direction = id_direction;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public Integer getId_fournisseur() {
        return id_fournisseur;
    }

    public void setId_fournisseur(Integer id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Integer getId_rubrique() {
        return id_rubrique;
    }

    public void setId_rubrique(Integer id_rubrique) {
        this.id_rubrique = id_rubrique;
    }

    public String getNomRubrique() {
        return nomRubrique;
    }

    public void setNomRubrique(String nomRubrique) {
        this.nomRubrique = nomRubrique;
    }
}
