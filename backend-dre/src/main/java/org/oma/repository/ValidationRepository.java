package org.oma.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.oma.model.Validation;
@ApplicationScoped
public class ValidationRepository implements PanacheRepository<Validation> {
}
