package org.dre.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.dre.model.TitreDepense;
import org.dre.repository.TitreDemandeRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TitreDemandeService {
    @Inject
    TitreDemandeRepository titreDemandeRepository;
    @Inject
    private EntityManager em;

    public Long getNextSequenceValue(String sequenceName) {
        return (Long) em.createNativeQuery("SELECT nextval(:sequenceName)").setParameter("sequenceName", sequenceName).getSingleResult();

    }
    @Transactional
    public void create(TitreDepense personnel) {
//  TitreDepense personnelMerged = titreDemandeRepository.getEntityManager().merge(personnel);
        //        TitreDepenseRepository.persist(TitreDepense);

        // Persister l'entité (éviter d'utiliser merge ici)
        titreDemandeRepository.persist(personnel);

        // Forcer explicitement la validation de la transaction
        em.flush();

    }

    public List<TitreDepense> getAll() {
        return titreDemandeRepository.listAll();
    }

    public List<TitreDepense> getAllByIdSession(Integer idSession) {
        List<TitreDepense> listTitre = this.getAll();
        List<TitreDepense> listTitreBySession = new ArrayList<>();

        for(TitreDepense titreDepense : listTitre){
            if(titreDepense.getIdSession() == idSession)
            {
                listTitreBySession.add(titreDepense);
            }
        }

        return listTitreBySession;
    }



    public TitreDepense getTitreDepenseById(Long id) {
        return titreDemandeRepository.findById(id);
    }

    @Transactional
    public void updateTitreDepense(TitreDepense titre_dmd) {
        titreDemandeRepository.getEntityManager().merge(titre_dmd);
    }

    @Transactional
    public boolean deleteTitreDepense(Long id) {
        TitreDepense titreDepense = titreDemandeRepository.findById(id);
        if (titreDepense != null) {
            titreDemandeRepository.delete(titreDepense);
            return true;
        }
        return false;
    }
}
