package org.dre.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.dre.model.Titre_dmd;
import org.dre.repository.Titre_dmdRepository;

import java.util.List;

@ApplicationScoped
public class Titre_dmdService {
    @Inject
    Titre_dmdRepository titre_dmdRepository;

    @Transactional
    public void create(Titre_dmd personnel) {
        Titre_dmd personnelMerged = titre_dmdRepository.getEntityManager().merge(personnel);
//        Titre_dmdRepository.persist(Titre_dmd);
    }

    public List<Titre_dmd> getAll() {
        return titre_dmdRepository.listAll();
    }



    public Titre_dmd getTitre_dmdById(Long id) {
        return titre_dmdRepository.findById(id);
    }

    @Transactional
    public void updateTitre_dmd(Titre_dmd Titre_dmd) {
        titre_dmdRepository.getEntityManager().merge(Titre_dmd);
    }

    @Transactional
    public boolean deleteTitre_dmd(Long id) {
        Titre_dmd Titre_dmd = titre_dmdRepository.findById(id);
        if (Titre_dmd != null) {
            titre_dmdRepository.delete(Titre_dmd);
            return true;
        }
        return false;
    }
}
