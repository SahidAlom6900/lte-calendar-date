package com.technoelevate.ess_lte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technoelevate.ess_lte.dto.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
	Person findByPersonId(int personId);
}
