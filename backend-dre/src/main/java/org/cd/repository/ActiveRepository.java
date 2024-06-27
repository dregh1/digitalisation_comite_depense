package org.cd.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.cd.model.Active;


@ApplicationScoped
public class ActiveRepository implements PanacheRepository<Active> {
}
