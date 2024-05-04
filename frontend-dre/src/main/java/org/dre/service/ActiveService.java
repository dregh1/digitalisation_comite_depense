package org.dre.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.dre.model.Active;
import org.dre.model.Brouillon;
import org.dre.repository.ActiveRepository;

import java.util.ArrayList;
import java.util.List;



@ApplicationScoped
public class ActiveService {
    @Inject
    ActiveRepository activeRepository;

    @Transactional
    public void create(Active active) {
        Active activeMerged = activeRepository.getEntityManager().merge(active);
//        ActiveRepository.persist(Active);
    }

    public List<Active> getAll() {
        return activeRepository.listAll();
    }



    public Active getActiveById(Long id) {
        return activeRepository.findById(id);
    }

    @Transactional
    public void updateActive(Active active) {
        activeRepository.getEntityManager().merge(active);
    }

    @Transactional
    public boolean deleteActive(Long id) {
        Active active = activeRepository.findById(id);
        if (active != null) {
            activeRepository.delete(active);
            return true;
        }
        return false;
    }
    public List<Active> getAllByIdDir(Integer id) {

        List<Active> actives= activeRepository.listAll();
        List<Active> activesById = new ArrayList<>();

        for(Active active : actives)
        {
            if(active.getIdDirection() == id)
            {
                activesById.add(active);
            }
        }
        return activesById;
    }

}
