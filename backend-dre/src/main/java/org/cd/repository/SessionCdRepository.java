package org.cd.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.cd.model.SessionCd;
@ApplicationScoped
public class SessionCdRepository implements PanacheRepository<SessionCd> {
}
