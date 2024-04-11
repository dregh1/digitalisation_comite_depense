package org.dre.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.dre.model.Reference;
import org.dre.model.TitreDepense;


@ApplicationScoped
public class TitreDemandeRepository implements   PanacheRepository<TitreDepense> {
}
