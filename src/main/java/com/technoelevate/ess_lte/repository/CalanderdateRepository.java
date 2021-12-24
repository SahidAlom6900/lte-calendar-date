package com.technoelevate.ess_lte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technoelevate.ess_lte.dto.CalenderDate;

@Repository
public interface CalanderdateRepository extends JpaRepository<CalenderDate, Integer> {
	CalenderDate findByCalanderDateId(int calanderDateId);
}
