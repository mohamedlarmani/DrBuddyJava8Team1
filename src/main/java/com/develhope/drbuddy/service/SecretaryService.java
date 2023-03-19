package com.develhope.drbuddy.service;

import com.develhope.drbuddy.entities.Secretary;
import com.develhope.drbuddy.repository.SecretaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class SecretaryService {

    @Autowired
    private SecretaryRepository secretaryRepository;

    public Secretary saveSecretary(Secretary secretary) {
        return secretaryRepository.save(secretary);
    }

    public void deleteSecretary(int secretaryId) {
        secretaryRepository.deleteById(secretaryId);
    }

    public Optional<Secretary> getSecretaryById(int secretaryId){
        Optional<Secretary> optionalSecretary = secretaryRepository.findById(secretaryId);
        if (optionalSecretary.isPresent()) {
            return optionalSecretary;
        } else {
            throw new EntityNotFoundException("Secretary with id " + secretaryId + " not found");
        }
    }
}