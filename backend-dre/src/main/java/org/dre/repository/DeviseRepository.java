package org.dre.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.dre.model.Active_dmd;
import org.dre.model.Devise;
@ApplicationScoped
public class DeviseRepository implements PanacheRepository<Devise> {
}
