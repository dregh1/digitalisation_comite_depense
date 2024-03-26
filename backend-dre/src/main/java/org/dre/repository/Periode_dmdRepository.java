package org.dre.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.dre.model.Periode_dmd;
import org.dre.model.Session_cd;
@ApplicationScoped
public class Periode_dmdRepository implements PanacheRepository<Periode_dmd>{
}


