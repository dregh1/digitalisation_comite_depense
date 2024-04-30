package org.dre.service;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.dre.model.Demande;
import org.dre.model.DetailDemande;
import org.dre.model.Direction;
import org.dre.repository.DemandeRepository;
import org.dre.repository.DetailDemandeRepository;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class DetailDemandeService {
    @Inject

    EntityManager entityManager;
    @Inject
    DetailDemandeRepository detailDemandeRepository;

    @Transactional
    public void create(DetailDemande detailDemande) {
        DetailDemande detailDemandeMerged = detailDemandeRepository.getEntityManager().merge(detailDemande);
    }

    public List<DetailDemande> getAll() {
        return detailDemandeRepository.listAll();
    }



    public DetailDemande getDetailDemandeById(Long id) {
        return detailDemandeRepository.findById(id);
    }

    @Transactional
    public void updateDetailDemande(DetailDemande detailDemande) {
        detailDemandeRepository.getEntityManager().merge(detailDemande);
    }

    @Transactional
    public boolean deleteDetailDemande(Long id) {
        DetailDemande detailDemande = detailDemandeRepository.findById(id);
        if (detailDemande != null) {
            detailDemandeRepository.delete(detailDemande);
            return true;
        }
        return false;
    }

    public List<DetailDemande> chercher(
                                String idDirection,
                                String motif,
                                String session,
                                String idFournisseur,
                                String dateDebut,
                                String dateFin,
                                String etat
                                ) {


        String sql =    "SELECT * FROM DetailDemande " ;

        if(!idDirection.isEmpty() || !motif.isEmpty() || !session.isEmpty() || !idFournisseur.isEmpty() || !dateDebut.isEmpty() || !dateFin.isEmpty() || !etat.isEmpty() )
        {
            sql+="where 1=1  ";
            if(!idDirection.isEmpty())
                sql+= " and idDirection ="+idDirection;
            if(!motif.isEmpty())
                sql+= " and motif like '%"+   motif+"%' ";
            if(!session.isEmpty())
                sql+= " and refsession ='"+session +"'";
            if(!idFournisseur.isEmpty())
                sql+= " and idFournisseur ="+idFournisseur;
            if(!dateDebut.isEmpty())
                sql+= " and debutsession>='" + dateDebut +"'";
            if(!dateFin.isEmpty())
                sql+= " and debutsession<='"+ dateFin +"'";
            if(!etat.isEmpty())
                sql+= " and etatfinal='"+etat+"'";
        }

        System.out.println(sql);

        Query query = entityManager.createNativeQuery(sql, DetailDemande.class);

        List<DetailDemande> detailDemandes = query.getResultList();

        return detailDemandes;
    }



}
