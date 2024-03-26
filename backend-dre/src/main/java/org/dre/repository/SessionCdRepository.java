package org.dre.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.dre.model.Personnel;
import org.dre.model.Session_cd;
@ApplicationScoped
public class SessionCdRepository implements PanacheRepository<Session_cd> {
}
