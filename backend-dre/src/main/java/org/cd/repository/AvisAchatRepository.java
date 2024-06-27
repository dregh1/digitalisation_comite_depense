package org.cd.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.cd.model.AvisAchat;

@ApplicationScoped
public class AvisAchatRepository implements PanacheRepository<AvisAchat> {
}
