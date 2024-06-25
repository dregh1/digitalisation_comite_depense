package org.oma.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.oma.model.Demande;

@ApplicationScoped
public class DemandeRepository implements PanacheRepository<Demande>{

}


