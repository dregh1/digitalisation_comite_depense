package org.dre.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.dre.model.Avis_achat;
import org.dre.repository.Avis_achatRepository;

import java.util.List;

@ApplicationScoped
public class Avis_achatService {

    @Inject
    Avis_achatRepository avis_achatRepository;

    @Transactional
    public void create(Avis_achat avis_achat) {
        Avis_achat avis_achatMerged = avis_achatRepository.getEntityManager().merge(avis_achat);
//        Avis_achatRepository.persist(Avis_achat);
    }

    public List<Avis_achat> getAll() {
        return avis_achatRepository.listAll();
    }



    public Avis_achat getAvis_achatById(Long id) {
        return avis_achatRepository.findById(id);
    }

    @Transactional
    public void updateAvis_achat(Avis_achat avis_achat) {
        avis_achatRepository.getEntityManager().merge(avis_achat);
    }

    @Transactional
    public boolean deleteAvis_achat(Long id) {
        Avis_achat avis_achat = avis_achatRepository.findById(id);
        if (avis_achat != null) {
            avis_achatRepository.delete(avis_achat);
            return true;
        }
        return false;
    }
}
