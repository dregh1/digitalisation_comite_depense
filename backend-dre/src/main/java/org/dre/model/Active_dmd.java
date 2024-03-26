package org.dre.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Active_dmd extends PanacheEntity {
  private  Long id;
  private  Integer id_titre;
  private  String  titre;
  private  String  motif;
  private  double montant_ht;
  private  boolean is_regularisation;
  private  String coms_prescripteur;
  private  Integer id_periode;
  private  String periode;
  private  Integer id_direction;
  private  String direction;
  private  Integer id_devise;
  private  String devise;
  private  Integer id_fournisseur;
  private  String fournisseur;
  private  Integer id_session;
  private  boolean etat_session;


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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getId_devise() {
        return id_devise;
    }

    public void setId_devise(Integer id_devise) {
        this.id_devise = id_devise;
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

    public Integer getId_session() {
        return id_session;
    }

    public void setId_session(Integer id_session) {
        this.id_session = id_session;
    }

    public boolean isEtat_session() {
        return etat_session;
    }

    public void setEtat_session(boolean etat_session) {
        this.etat_session = etat_session;
    }
}
