package org.dre.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.dre.model.Personnel;

@ApplicationScoped
public class PersonnelRepository implements PanacheRepository<Personnel> {

}
