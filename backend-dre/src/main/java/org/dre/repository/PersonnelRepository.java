package org.dre.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.dre.model.Personnel;
import org.dre.model.Session_cd;

@ApplicationScoped
public class PersonnelRepository implements PanacheRepository<Personnel> {
//    public Personnel findById(Long id) {
//        return findById(id);
//    }
//
//    public Personnel findByEmail(String email) {
//        return find("email", email).firstResult();
//    }
}
