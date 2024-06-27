package org.cd.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.cd.model.TitreDepense;


@ApplicationScoped
public class TitreDemandeRepository implements   PanacheRepository<TitreDepense> {
}
