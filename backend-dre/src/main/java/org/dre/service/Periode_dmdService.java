package org.dre.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.dre.model.Periode_dmd;
import org.dre.repository.Periode_dmdRepository;
import org.dre.repository.Periode_dmdRepository;

import java.util.List;

@ApplicationScoped
public class Periode_dmdService {
    @Inject
    Periode_dmdRepository Periode_dmdRepository;

    @Transactional
    public void create(Periode_dmd personnel) {
        Periode_dmd personnelMerged = Periode_dmdRepository.getEntityManager().merge(personnel);
//        Periode_dmdRepository.persist(Periode_dmd);
    }

    public List<Periode_dmd> getAll() {
        return Periode_dmdRepository.listAll();
    }



    public Periode_dmd getPeriode_dmdById(Long id) {
        return Periode_dmdRepository.findById(id);
    }

    @Transactional
    public void updatePeriode_dmd(Periode_dmd Periode_dmd) {
        Periode_dmdRepository.getEntityManager().merge(Periode_dmd);
    }

    @Transactional
    public boolean deletePeriode_dmd(Long id) {
        Periode_dmd Periode_dmd = Periode_dmdRepository.findById(id);
        if (Periode_dmd != null) {
            Periode_dmdRepository.delete(Periode_dmd);
            return true;
        }
        return false;
    }
}