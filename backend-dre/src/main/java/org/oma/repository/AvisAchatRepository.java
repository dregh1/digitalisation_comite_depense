package org.oma.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.oma.model.AvisAchat;

@ApplicationScoped
public class AvisAchatRepository implements PanacheRepository<AvisAchat> {
}
