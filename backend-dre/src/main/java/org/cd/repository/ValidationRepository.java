package org.cd.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.cd.model.Validation;
@ApplicationScoped
public class ValidationRepository implements PanacheRepository<Validation> {
}
