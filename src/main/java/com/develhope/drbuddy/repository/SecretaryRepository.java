package com.develhope.drbuddy.repository;

import com.develhope.drbuddy.entities.Secretary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretaryRepository extends JpaRepository<Secretary, Integer> {
}
