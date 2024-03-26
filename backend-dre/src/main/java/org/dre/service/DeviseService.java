package org.dre.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.dre.model.Devise;
import org.dre.repository.DeviseRepository;

import java.util.List;

@ApplicationScoped
public class DeviseService {
    @Inject
    DeviseRepository deviseRepository;

    @Transactional
    public void create(Devise personnel) {
        Devise personnelMerged = deviseRepository.getEntityManager().merge(personnel);
//        DeviseRepository.persist(Devise);
    }

    public List<Devise> getAll() {
        return deviseRepository.listAll();
    }



    public Devise getDeviseById(Long id) {
        return deviseRepository.findById(id);
    }

    @Transactional
    public void updateDevise(Devise devise) {
        deviseRepository.getEntityManager().merge(devise);
    }

    @Transactional
    public boolean deleteDevise(Long id) {
        Devise devise = deviseRepository.findById(id);
        if (devise != null) {
            deviseRepository.delete(devise);
            return true;
        }
        return false;
    }
}
