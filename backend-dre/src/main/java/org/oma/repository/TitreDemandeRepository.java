package org.oma.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.oma.model.TitreDepense;


@ApplicationScoped
public class TitreDemandeRepository implements   PanacheRepository<TitreDepense> {
}
