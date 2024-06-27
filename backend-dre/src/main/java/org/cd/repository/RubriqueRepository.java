package org.cd.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.cd.model.Rubrique;

@ApplicationScoped
public class RubriqueRepository implements PanacheRepository<Rubrique>{
}


