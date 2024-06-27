package org.cd.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.cd.model.Brouillon;

@ApplicationScoped
public class BrouillonRepository implements PanacheRepository<Brouillon> {

}
