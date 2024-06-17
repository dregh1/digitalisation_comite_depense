package org.dre.service;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.dre.model.Brouillon;
import org.dre.repository.BrouillonRepository;
import org.dre.repository.BrouillonRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BrouillonService {


    @PersistenceContext
    EntityManager entityManager;

    @Inject
    BrouillonRepository brouillonRepository;

    @Transactional
    public void create(Brouillon brouillon) {
        Brouillon brouillonMerged = brouillonRepository.getEntityManager().merge(brouillon);
//        BrouillonRepository.persist(Brouillon);
    }

    public List<Brouillon> getAll() {
//        PanacheQuery<Brouillon> livingPersons =

        return brouillonRepository.listAll();
    }

    @Transactional
    public List<Brouillon> trouverBrouillonsParPage(int page, int taillePage) {
        TypedQuery<Brouillon> query = entityManager.createQuery(
                "SELECT p FROM Brouillon p",
                Brouillon.class
        );
        query.setFirstResult((page - 1) * taillePage);
        query.setMaxResults(taillePage);
        return query.getResultList();
    }

    public Long compterTotalBrouillons() {
        return entityManager.createQuery(
                "SELECT COUNT(p) FROM Brouillon p",
                Long.class
        ).getSingleResult();
    }


//    public List<Brouillon> getAllByIdDir(Integer id) {
//
//        List<Brouillon> brouillons= brouillonRepository.listAll();
//        List<Brouillon> brouillonsById = new ArrayList<>();
//
//        for(Brouillon brouillon : brouillons)
//        {
//            if(brouillon.getIdDirection() == id)
//            {
//                brouillonsById.add(brouillon);
//            }
//        }
//        return brouillonsById;
//    }


    public Brouillon getBrouillonById(Long id) {
        return brouillonRepository.findById(id);
    }

    @Transactional
    public void updateBrouillon(Brouillon brouillon) {
        brouillonRepository.getEntityManager().merge(brouillon);
    }

    @Transactional
    public boolean deleteBrouillon(Long id) {
        Brouillon brouillon = brouillonRepository.findById(id);
        if (brouillon != null) {
            brouillonRepository.delete(brouillon);
            return true;
        }
        return false;
    }

}
