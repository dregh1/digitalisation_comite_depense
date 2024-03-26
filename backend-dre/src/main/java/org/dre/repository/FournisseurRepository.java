package org.dre.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.dre.model.Fournisseur;
import org.dre.model.Periode_dmd;
@ApplicationScoped
public class FournisseurRepository implements PanacheRepository<Fournisseur> {
}
