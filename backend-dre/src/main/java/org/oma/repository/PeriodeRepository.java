package org.oma.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.oma.model.Periode;
@ApplicationScoped
public class PeriodeRepository implements PanacheRepository<Periode>{
}


