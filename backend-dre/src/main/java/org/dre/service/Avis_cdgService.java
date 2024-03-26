package org.dre.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.dre.model.Avis_cdg;
import org.dre.repository.Avis_cdgRepository;

import java.util.List;

@ApplicationScoped
public class Avis_cdgService {

    @Inject
    Avis_cdgRepository avis_cdgRepository;

    @Transactional
    public void create(Avis_cdg avis_cdg) {
        Avis_cdg avis_cdgMerged = avis_cdgRepository.getEntityManager().merge(avis_cdg);
//        Avis_cdgRepository.persist(Avis_cdg);
    }

    public List<Avis_cdg> getAll() {
        return avis_cdgRepository.listAll();
    }



    public Avis_cdg getAvis_cdgById(Long id) {
        return avis_cdgRepository.findById(id);
    }

    @Transactional
    public void updateAvis_cdg(Avis_cdg avis_cdg) {
        avis_cdgRepository.getEntityManager().merge(avis_cdg);
    }

    @Transactional
    public boolean deleteAvis_cdg(Long id) {
        Avis_cdg avis_cdg = avis_cdgRepository.findById(id);
        if (avis_cdg != null) {
            avis_cdgRepository.delete(avis_cdg);
            return true;
        }
        return false;
    }
}
