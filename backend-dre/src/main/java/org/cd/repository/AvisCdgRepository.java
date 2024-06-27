package org.cd.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.cd.model.AvisCdg;
@ApplicationScoped
public class AvisCdgRepository implements PanacheRepository<AvisCdg> {

}
