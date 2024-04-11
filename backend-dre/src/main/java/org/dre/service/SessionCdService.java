package org.dre.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.dre.model.Personnel;
import org.dre.model.SessionCd;
import org.dre.repository.PersonnelRepository;
import org.dre.repository.SessionCdRepository;

import java.util.List;
@ApplicationScoped
public class SessionCdService {
    @Inject
    SessionCdRepository sessionCdRepository;
//    @Transactional
//    public void createSessionCd(Session sessionCd) {
//        Session sessionCdMerged = sessionCdRepository.getEntityManager().merge(sessionCd);
////        personnelRepository.persist(Personnel);
//    }
    public List<SessionCd> getAllSessionCd() {
        return sessionCdRepository.listAll();
    }

    // CREAT SESSION
    @Transactional
    public void createSessionCd(SessionCd sessionCd) {
        SessionCd sessionCdMerged = sessionCdRepository.getEntityManager().merge(sessionCd);
    }

}
