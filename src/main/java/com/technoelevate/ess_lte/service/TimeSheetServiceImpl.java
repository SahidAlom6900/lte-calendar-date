package com.technoelevate.ess_lte.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technoelevate.ess_lte.dto.Person;
import com.technoelevate.ess_lte.dto.TimeSheet;
import com.technoelevate.ess_lte.exception.CustomException;
import com.technoelevate.ess_lte.repository.PersonRepository;
import com.technoelevate.ess_lte.repository.TimeSheetRepository;
import com.technoelevate.ess_lte.response.Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TimeSheetServiceImpl implements TimeSheetService {

	@Autowired
	private TimeSheetRepository timeSheetRepository;
	@Autowired
	private PersonRepository personRepository;

	@Override
	public TimeSheet saveTimeSheet(TimeSheet timeSheet) {
		List<TimeSheet> timeSheets = new ArrayList<>();
		timeSheets.add(timeSheet);
		Person person = new Person();
		person.setPersonId(timeSheet.getPerson().getPersonId());
		LocalTime loginTime = timeSheet.getLoginTime();
		LocalTime logoutTime = timeSheet.getLogoutTime();
		long logoutTime_minute = logoutTime.getHour() * 60 + logoutTime.getMinute();
		long loginTime_minute = loginTime.getHour() * 60 + loginTime.getMinute();
		long totalWorkingHourMin = logoutTime_minute - loginTime_minute;
		int hour = (int) (totalWorkingHourMin / 60);
		int minute = (int) (totalWorkingHourMin - hour * 60);
		LocalTime totalWorkingHour = LocalTime.of(hour, minute);
		person.setTimeSheets(timeSheets);
		timeSheet.setPerson(person);
		timeSheet.setTotalWorkingTime(totalWorkingHour);
		personRepository.save(person);
		timeSheetRepository.save(timeSheet);
		return timeSheet;
	}

	@Override
	public TimeSheet deleteTimeSheet(int timeSheetId) {
		TimeSheet timeSheet = timeSheetRepository.findByTimeSheetId(timeSheetId);
		if (timeSheet != null) {
			timeSheetRepository.delete(timeSheet);
			return timeSheet;
		}
		log.error(Message.ID_NOT_FOUND);
		throw new CustomException(Message.ID_NOT_FOUND);
	}

	@Override
	public TimeSheet getTimeSheet(int timeSheetId) {
		TimeSheet timeSheet = timeSheetRepository.findByTimeSheetId(timeSheetId);
		if (timeSheet != null) {
			return timeSheet;
		}
		log.error(Message.ID_NOT_FOUND);
		throw new CustomException(Message.ID_NOT_FOUND);
	}

	@Override
	public TimeSheet editTimeSheet(TimeSheet timeSheet) {
		int personId = timeSheet.getPerson().getPersonId();
		Person person = personRepository.findByPersonId(personId);
		if (person != null) {
			List<TimeSheet> timeSheets = new ArrayList<>();
			timeSheets.add(timeSheet);
			person.setTimeSheets(timeSheets);
			timeSheet.setPerson(person);
			personRepository.save(person);
			timeSheetRepository.save(timeSheet);
			return timeSheet;
		}
		log.error(Message.ID_NOT_FOUND);
		throw new CustomException(Message.ID_NOT_FOUND);
	}

	@Override
	public List<TimeSheet> getAllTimeSheet(int personId) {
		Person person = personRepository.findByPersonId(personId);
		if (person != null) {
			return person.getTimeSheets();
		}
		log.error(Message.ID_NOT_FOUND);
		throw new CustomException(Message.ID_NOT_FOUND);
	}

	
	
}
