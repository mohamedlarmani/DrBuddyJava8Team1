package com.develhope.drbuddy.repository;

import com.develhope.drbuddy.entities.Person;
import com.develhope.drbuddy.entities.Secretary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Long, Person> {
    Optional<Person> findByEmail(String email);
}
