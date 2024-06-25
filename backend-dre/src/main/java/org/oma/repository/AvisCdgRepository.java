package org.oma.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.oma.model.AvisCdg;
@ApplicationScoped
public class AvisCdgRepository implements PanacheRepository<AvisCdg> {

}
