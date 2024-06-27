package org.cd.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.cd.model.DetailDemande;

@ApplicationScoped
public class DetailDemandeRepository implements PanacheRepository<DetailDemande> {
}
