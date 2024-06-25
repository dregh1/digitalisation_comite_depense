package org.oma.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.oma.model.DetailDemande;

@ApplicationScoped
public class DetailDemandeRepository implements PanacheRepository<DetailDemande> {
}
