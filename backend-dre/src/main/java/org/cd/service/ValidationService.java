package org.cd.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.cd.model.Validation;
import org.cd.repository.ValidationRepository;

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
