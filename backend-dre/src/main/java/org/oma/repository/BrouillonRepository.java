package org.oma.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.oma.model.Brouillon;

@ApplicationScoped
public class BrouillonRepository implements PanacheRepository<Brouillon> {

}
