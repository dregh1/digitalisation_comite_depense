package org.dre.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.dre.model.Demande;
import org.dre.model.SessionCd;
import org.dre.repository.SessionCdRepository;
import org.hibernate.Session;

import java.util.List;
@ApplicationScoped
public class SessionCdService {
    @Inject
    SessionCdRepository sessionCdRepository;

    public List<SessionCd> getAllSessionCd() {
        return sessionCdRepository.listAll();
    }

    // CREAT SESSION
    @Transactional
    public void createSessionCd(SessionCd sessionCd) {
        SessionCd sessionCdMerged = sessionCdRepository.getEntityManager().merge(sessionCd);
    }

    public List<SessionCd> getAll() {
        return sessionCdRepository.listAll();
    }

    public SessionCd getActiveSession(Integer id) {
        List<SessionCd> allList = this.getAll();

        for(SessionCd sessionCd : allList )
        {
            if(sessionCd.getIdDirection().equals(id) && !sessionCd.isEstFerme())
            {
                return sessionCd;
            }
        }
        return null;
    }



    public SessionCd getSessionActive(Integer idDirection) {
        List <SessionCd> sessionCds = this.getAll();
        for(SessionCd session : sessionCds)
        {
            if(session.getIdDirection().equals(idDirection) && !session.isEstFerme())
            {
                return session;
            }
        }
        return null;
    }


}
