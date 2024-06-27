package org.cd.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.cd.model.SessionCd;
import org.cd.repository.SessionCdRepository;

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

    public SessionCd getActiveSession(Integer idDirection) {
        List<SessionCd> allList = this.getAll();

        for(SessionCd sessionCd : allList )
        {
            if( !sessionCd.isEstFerme())
            {
                return sessionCd;
            }
        }
        return null;
    }

    @Transactional
    public void updateSessionCd(SessionCd sessionCd) {
        sessionCdRepository.getEntityManager().merge(sessionCd);
    }

    public boolean checkSession(Integer idDirection) {
        List <SessionCd> sessionCds = this.getAll();
        for(SessionCd session : sessionCds)
        {
            if(!session.isEstFerme())
            {
                return true;
            }
        }
        return false;
    }

    public SessionCd getidSession() {
        SessionCd s = new SessionCd();
        List <SessionCd> sessionCds = this.getAll();
        for(SessionCd session : sessionCds)
        {
            if(!session.isEstFerme())
            {
                return session;
            }
        }
        return s;
    }


}
