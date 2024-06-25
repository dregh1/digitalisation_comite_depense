package org.oma.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.oma.model.Rubrique;
import org.oma.model.SessionCd;
@ApplicationScoped
public class RubriqueRepository implements PanacheRepository<Rubrique>{
}


