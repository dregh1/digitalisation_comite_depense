package org.dre.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import org.dre.model.Sousrubrique;

@ApplicationScoped
public class SousrubriqueRepository implements PanacheRepository<Sousrubrique>{
}


