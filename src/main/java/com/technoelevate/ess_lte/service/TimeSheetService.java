package com.technoelevate.ess_lte.service;

import java.util.List;

import com.technoelevate.ess_lte.dto.TimeSheet;

public interface TimeSheetService {
	TimeSheet saveTimeSheet(TimeSheet timeSheet);

	TimeSheet deleteTimeSheet( int timeSheetId);

	TimeSheet getTimeSheet( int timeSheetId);

	TimeSheet editTimeSheet(TimeSheet timeSheet);
	
	List<TimeSheet> getAllTimeSheet(int personId);
	
}
