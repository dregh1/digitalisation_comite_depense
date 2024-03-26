package org.dre.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.dre.model.Avis_achat;
import org.dre.model.Avis_cdg;
@ApplicationScoped
public class Avis_cdgRepository implements PanacheRepository<Avis_cdg> {
}
