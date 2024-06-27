package org.cd.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.cd.model.Demande;
import org.cd.repository.DemandeRepository;

import java.util.List;

@ApplicationScoped
public class DemandeService
{
    @Inject
    DemandeRepository demandeRepository;

    @Transactional
    public void create(Demande personnel) {
        Demande personnelMerged = demandeRepository.getEntityManager().merge(personnel);
//        DemandeRepository.persist(Demande);
    }

    public List<Demande> getAll() {
        return demandeRepository.listAll();
    }



    public Demande getDemandeById(Long id) {
        return demandeRepository.findById(id);
    }



    @Transactional
    public void updateDemande(Demande demande) {
        demandeRepository.getEntityManager().merge(demande);
    }

    @Transactional
    public void deleteDemande(Long id) {
        Demande demande = demandeRepository.findById(id);
        if (demande != null) {
            demande.setEstSupprime(true);
            demandeRepository.getEntityManager().merge(demande);
        }
    }

    @Transactional
    public boolean delete(Long id) {
        Demande demande = demandeRepository.findById(id);
        if (demande != null) {
            demandeRepository.delete(demande);
            return true;
        }
        return false;
    }



}