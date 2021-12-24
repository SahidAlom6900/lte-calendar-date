package com.technoelevate.ess_lte.service;

import com.technoelevate.ess_lte.dto.CalenderDate;
import com.technoelevate.ess_lte.dto.CalenderDateDto;
import com.technoelevate.ess_lte.dto.TimeSheet;

public interface CalanderdateService {

	CalenderDate saveCalanderDate(CalenderDateDto calanderDate);

	CalenderDate deleteCalanderDate(int calanderDateId);

	CalenderDate getCalanderDate(int calanderDateId);

	TimeSheet getCalanderDates(int timeSheetId);

}
