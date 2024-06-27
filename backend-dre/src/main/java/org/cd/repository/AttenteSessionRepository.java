package org.cd.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.cd.model.AttenteSession;

@ApplicationScoped
public class AttenteSessionRepository implements PanacheRepository<AttenteSession> {
}
