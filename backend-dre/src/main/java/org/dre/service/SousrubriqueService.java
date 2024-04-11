package org.dre.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.dre.model.SessionCd;
import org.dre.model.Sousrubrique;
import org.dre.repository.SessionCdRepository;
import org.dre.repository.SousrubriqueRepository;

import java.util.List;
@ApplicationScoped
public class SousrubriqueService {
    @Inject
    SousrubriqueRepository sousrubriqueRepository;
    //    @Transactional
//    public void createSessionCd(SessionCd sessionCd) {
//        SessionCd sessionCdMerged = sessionCdRepository.getEntityManager().merge(sessionCd);
////        personnelRepository.persist(Personnel);
//    }
    public List<Sousrubrique> getAll() {
        return sousrubriqueRepository.listAll();
    }

    // CREAT SESSION
    @Transactional
    public void create(SessionCd sessionCd) {
        SessionCd sessionCdMerged = sousrubriqueRepository.getEntityManager().merge(sessionCd);
    }
}
