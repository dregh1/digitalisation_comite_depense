package org.dre.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.dre.model.Reference;
import org.dre.model.Titre_dmd;
@ApplicationScoped
public class Titre_dmdRepository implements   PanacheRepository<Titre_dmd> {
}
