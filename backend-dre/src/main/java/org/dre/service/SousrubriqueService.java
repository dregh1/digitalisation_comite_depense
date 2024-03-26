package org.dre.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.dre.model.Session_cd;
import org.dre.model.Sousrubrique;
import org.dre.repository.SessionCdRepository;
import org.dre.repository.SousrubriqueRepository;

import java.util.List;
@ApplicationScoped
public class SousrubriqueService {
    @Inject
    SousrubriqueRepository sousrubriqueRepository;
    //    @Transactional
//    public void createSessionCd(Session_cd sessionCd) {
//        Session_cd sessionCdMerged = sessionCdRepository.getEntityManager().merge(sessionCd);
////        personnelRepository.persist(Personnel);
//    }
    public List<Sousrubrique> getAll() {
        return sousrubriqueRepository.listAll();
    }

    // CREAT SESSION
    @Transactional
    public void create(Session_cd sessionCd) {
        Session_cd sessionCdMerged = sousrubriqueRepository.getEntityManager().merge(sessionCd);
    }
}
