package com.develhope.drbuddy.repository;

import com.develhope.drbuddy.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository <Doctor, Integer> {
    Optional<Doctor> findByEmail(String email);
}
