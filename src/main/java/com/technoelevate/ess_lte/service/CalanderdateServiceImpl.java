package com.technoelevate.ess_lte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technoelevate.ess_lte.dto.CalenderDate;
import com.technoelevate.ess_lte.dto.CalenderDateDto;
import com.technoelevate.ess_lte.dto.TimeSheet;
import com.technoelevate.ess_lte.exception.CustomException;
import com.technoelevate.ess_lte.repository.CalanderdateRepository;
import com.technoelevate.ess_lte.repository.TimeSheetRepository;
import com.technoelevate.ess_lte.response.Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CalanderdateServiceImpl implements CalanderdateService {

	@Autowired
	private CalanderdateRepository calanderdateRepository;
	@Autowired
	private TimeSheetRepository timeSheetRepository;

	@Override
	public CalenderDate saveCalanderDate(CalenderDateDto calanderDateDto) {
		TimeSheet timeSheet = timeSheetRepository.findByTimeSheetId(calanderDateDto.getTimeSheet().getTimeSheetId());
		CalenderDate calenderDate = new CalenderDate(calanderDateDto.getCalanderDateId(),
				calanderDateDto.getDayMessage(), calanderDateDto.getLoginTime(), calanderDateDto.getLogoutTime(),
				calanderDateDto.getDate(), calanderDateDto.getDailyTaskDetails(), timeSheet);
		log.info(Message.DATA_ADDED);
		return calanderdateRepository.save(calenderDate);
	}

	@Override
	public CalenderDate deleteCalanderDate(int calanderDateId) {
		CalenderDate calanderDate = calanderdateRepository.findByCalanderDateId(calanderDateId);
		if (calanderDate != null) {
			calanderdateRepository.delete(calanderDate);
			return calanderDate;
		}
		log.error(Message.ID_NOT_FOUND);
		throw new CustomException(Message.ID_NOT_FOUND);
	}

	@Override
	public CalenderDate getCalanderDate(int calanderDateId) {
		CalenderDate calanderDate = calanderdateRepository.findByCalanderDateId(calanderDateId);
		if (calanderDate != null) {
			return calanderDate;
		}
		log.error(Message.ID_NOT_FOUND);
		throw new CustomException(Message.ID_NOT_FOUND);
	}

	@Override
	public TimeSheet getCalanderDates(int timeSheetId) {
		TimeSheet timeSheet = timeSheetRepository.findByTimeSheetId(timeSheetId);
		if (timeSheet != null) {
			return timeSheet;
		}
		log.error(Message.ID_NOT_FOUND);
		throw new CustomException(Message.ID_NOT_FOUND);
	}

}
