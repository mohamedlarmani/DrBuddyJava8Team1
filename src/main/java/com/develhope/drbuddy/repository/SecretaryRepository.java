package com.develhope.drbuddy.repository;

import com.develhope.drbuddy.entities.Patient;
import com.develhope.drbuddy.entities.Secretary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecretaryRepository extends JpaRepository <Secretary, Integer> {
    Optional<Secretary> findByEmail(String email);
}
