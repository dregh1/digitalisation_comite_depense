package org.cd.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.cd.model.Decision;

@ApplicationScoped
public class DecisionRepository implements PanacheRepository<Decision> {
}
