package org.dre.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.dre.model.Personnel;
import org.dre.model.Session_cd;
import org.dre.repository.PersonnelRepository;
import org.dre.repository.SessionCdRepository;

import java.util.List;
@ApplicationScoped
public class SessionCdService {
    @Inject
    SessionCdRepository sessionCdRepository;
//    @Transactional
//    public void createSessionCd(Session_cd sessionCd) {
//        Session_cd sessionCdMerged = sessionCdRepository.getEntityManager().merge(sessionCd);
////        personnelRepository.persist(Personnel);
//    }
    public List<Session_cd> getAllSessionCd() {
        return sessionCdRepository.listAll();
    }

    // CREAT SESSION
    @Transactional
    public void createSessionCd(Session_cd sessionCd) {
        Session_cd sessionCdMerged = sessionCdRepository.getEntityManager().merge(sessionCd);
    }

}
