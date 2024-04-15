package org.dre.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity

public class Active extends PanacheEntity {
    private   Long id ;
    private  Integer idTitre ;
    private    String  titre ;
    private  String motif ;
    private  double montantHt ;
    private  String  typeReference ;
    private  String  reference ;
    private  boolean estRegularisation ;
    private  String  comsPrescripteur ;
    private  Integer idPeriode ;
    private  String  periode ;
    private  Integer idDirection ;
    private  String  devise ;
    private  Integer idFournisseur ;

    private Integer idRubrique;
    private String nomRubrique;
    private  String  fournisseur;
    private String sousRubrique;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSousRubrique() {
        return sousRubrique;
    }

    public void setSousRubrique(String sousRubrique) {
        this.sousRubrique = sousRubrique;
    }

    public Integer getIdTitre() {
        return idTitre;
    }

    public void setIdTitre(Integer idTitre) {
        this.idTitre = idTitre;
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

    public double getMontantHt() {
        return montantHt;
    }

    public void setMontantHt(double montantHt) {
        this.montantHt = montantHt;
    }

    public String getTypeReference() {
        return typeReference;
    }

    public void setTypeReference(String typeReference) {
        this.typeReference = typeReference;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public boolean isEstRegularisation() {
        return estRegularisation;
    }

    public void setEstRegularisation(boolean estRegularisation) {
        this.estRegularisation = estRegularisation;
    }

    public String getComsPrescripteur() {
        return comsPrescripteur;
    }

    public void setComsPrescripteur(String comsPrescripteur) {
        this.comsPrescripteur = comsPrescripteur;
    }

    public Integer getIdPeriode() {
        return idPeriode;
    }

    public void setIdPeriode(Integer idPeriode) {
        this.idPeriode = idPeriode;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public Integer getIdDirection() {
        return idDirection;
    }

    public void setIdDirection(Integer idDirection) {
        this.idDirection = idDirection;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public Integer getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(Integer idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public Integer getIdRubrique() {
        return idRubrique;
    }

    public void setIdRubrique(Integer idRubrique) {
        this.idRubrique = idRubrique;
    }

    public String getNomRubrique() {
        return nomRubrique;
    }

    public void setNomRubrique(String nomRubrique) {
        this.nomRubrique = nomRubrique;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }
}
