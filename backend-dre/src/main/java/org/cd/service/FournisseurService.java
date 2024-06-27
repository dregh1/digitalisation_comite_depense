package org.cd.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.cd.model.Fournisseur;
import org.cd.repository.FournisseurRepository;

import java.util.List;

@ApplicationScoped
public class FournisseurService {
    @Inject
    FournisseurRepository fournisseurRepository;

    @Transactional
    public void create(Fournisseur fournisseur) {
        Fournisseur fournisseurMerged = fournisseurRepository.getEntityManager().merge(fournisseur);
//        FournisseurRepository.persist(Fournisseur);
    }

    public List<Fournisseur> getAll() {
        return fournisseurRepository.listAll();
    }



    public Fournisseur getFournisseurById(Long id) {
        return fournisseurRepository.findById(id);
    }

    @Transactional
    public void updateFournisseur(Fournisseur Fournisseur) {
        fournisseurRepository.getEntityManager().merge(Fournisseur);
    }

    @Transactional
    public boolean deleteFournisseur(Long id) {
        Fournisseur Fournisseur = fournisseurRepository.findById(id);
        if (Fournisseur != null) {
            fournisseurRepository.delete(Fournisseur);
            return true;
        }
        return false;
    }


}
