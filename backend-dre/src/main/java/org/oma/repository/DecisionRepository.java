package org.oma.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.oma.model.Decision;

@ApplicationScoped
public class DecisionRepository implements PanacheRepository<Decision> {
}
