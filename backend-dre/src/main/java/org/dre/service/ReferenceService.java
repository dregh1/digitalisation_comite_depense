package org.dre.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.dre.model.Reference;
import org.dre.repository.ReferenceRepository;

import java.util.List;
@ApplicationScoped
public class ReferenceService {

    @Inject
    ReferenceRepository referenceRepository;

    @Transactional
    public void create(Reference reference) {
        Reference referenceMerged = referenceRepository.getEntityManager().merge(reference);
//        ReferenceRepository.persist(Reference);
    }

    public List<Reference> getAll() {
        return referenceRepository.listAll();
    }



    public Reference getReferenceById(Long id) {
        return referenceRepository.findById(id);
    }

    @Transactional
    public void updateReference(Reference reference) {
        referenceRepository.getEntityManager().merge(reference);
    }

    @Transactional
    public boolean deleteReference(Long id) {
        Reference reference = referenceRepository.findById(id);
        if (reference != null) {
            referenceRepository.delete(reference);
            return true;
        }
        return false;
    }
}
