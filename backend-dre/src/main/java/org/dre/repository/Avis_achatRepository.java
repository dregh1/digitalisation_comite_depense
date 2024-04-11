package org.dre.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.dre.model.Avis_achat;

@ApplicationScoped
public class Avis_achatRepository implements PanacheRepository<Avis_achat> {
}
