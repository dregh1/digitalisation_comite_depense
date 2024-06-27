package org.cd.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.cd.model.Periode;
@ApplicationScoped
public class PeriodeRepository implements PanacheRepository<Periode>{
}


