package org.dre.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.dre.model.Active_dmd;
import org.dre.repository.Active_dmdRepository;

import java.util.List;



@ApplicationScoped
public class Active_dmdService {
    @Inject
    Active_dmdRepository active_dmdRepository;

    @Transactional
    public void create(Active_dmd active_dmd) {
        Active_dmd active_dmdMerged = active_dmdRepository.getEntityManager().merge(active_dmd);
//        Active_dmdRepository.persist(Active_dmd);
    }

    public List<Active_dmd> getAll() {
        return active_dmdRepository.listAll();
    }



    public Active_dmd getActive_dmdById(Long id) {
        return active_dmdRepository.findById(id);
    }

    @Transactional
    public void updateActive_dmd(Active_dmd active_dmd) {
        active_dmdRepository.getEntityManager().merge(active_dmd);
    }

    @Transactional
    public boolean deleteActive_dmd(Long id) {
        Active_dmd active_dmd = active_dmdRepository.findById(id);
        if (active_dmd != null) {
            active_dmdRepository.delete(active_dmd);
            return true;
        }
        return false;
    }

}
