package org.cd.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.cd.model.Fournisseur;
@ApplicationScoped
public class FournisseurRepository implements PanacheRepository<Fournisseur> {
}
