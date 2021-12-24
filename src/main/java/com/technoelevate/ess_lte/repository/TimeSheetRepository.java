package com.technoelevate.ess_lte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technoelevate.ess_lte.dto.TimeSheet;

@Repository
public interface TimeSheetRepository extends JpaRepository<TimeSheet, Integer> {
	TimeSheet findByTimeSheetId(int timeSheetId);
}
