package org.cd.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.cd.model.Demande;

@ApplicationScoped
public class DemandeRepository implements PanacheRepository<Demande>{

}


