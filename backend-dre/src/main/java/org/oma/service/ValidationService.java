package org.oma.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.oma.model.Validation;
import org.oma.repository.ValidationRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ValidationService {
    @Inject
    ValidationRepository validationRepository;

    public List<Validation> getAll() {
        return validationRepository.listAll();
    }

    public Validation getById(Long id) {
        return validationRepository.findById(id);
    }




}
