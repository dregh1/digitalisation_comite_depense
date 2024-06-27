package org.cd.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.cd.model.Rubrique;
import org.cd.repository.RubriqueRepository;

import java.util.List;

@ApplicationScoped
public class RubriqueService {
    @Inject
    RubriqueRepository rubriqueRepository;

    @Transactional
    public void create(Rubrique personnel) {
        Rubrique personnelMerged = rubriqueRepository.getEntityManager().merge(personnel);
//        RubriqueRepository.persist(Rubrique);
    }

    public List<Rubrique> getAll() {
        return rubriqueRepository.listAll();
    }



    public Rubrique getRubriqueById(Long id) {
        return rubriqueRepository.findById(id);
    }

    @Transactional
    public void updateRubrique(Rubrique Rubrique) {
        rubriqueRepository.getEntityManager().merge(Rubrique);
    }

    @Transactional
    public boolean deleteRubrique(Long id) {
        Rubrique Rubrique = rubriqueRepository.findById(id);
        if (Rubrique != null) {
            rubriqueRepository.delete(Rubrique);
            return true;
        }
        return false;
    }
}