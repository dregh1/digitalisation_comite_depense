package org.dre.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.dre.model.Demande;
import org.dre.model.DetailDemande;
import org.dre.repository.DemandeRepository;
import org.dre.repository.DetailDemandeRepository;

import java.util.List;
@ApplicationScoped
public class DetailDemandeService {
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
}
