package org.oma.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.oma.model.Direction;
@ApplicationScoped
public class DirectionRepository  implements PanacheRepository<Direction> {
}
