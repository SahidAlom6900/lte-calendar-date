package com.technoelevate.ess_lte.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technoelevate.ess_lte.dto.CalenderDate;
import com.technoelevate.ess_lte.dto.CalenderDateDto;
import com.technoelevate.ess_lte.dto.TimeSheet;
import com.technoelevate.ess_lte.exception.CustomException;
import com.technoelevate.ess_lte.response.Message;
import com.technoelevate.ess_lte.response.ResponseMessage;
import com.technoelevate.ess_lte.service.CalanderdateService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@RequestMapping("/api/v1/calander-date")
public class CalanderDateController {
	@Autowired
	private CalanderdateService calanderdateService;

	@PostMapping(path = "/add")
	public ResponseEntity<?> saveTimeSheet(@Valid @RequestBody CalenderDateDto calanderDate) {
		CalenderDate saveCalanderDate = calanderdateService.saveCalanderDate(calanderDate);
		if (saveCalanderDate != null) {
			ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), new Date(), false,
					Message.DATA_ADDED, saveCalanderDate);
			log.info(Message.DATA_ADDED);
			return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		}
		log.error(Message.SOMETHING_WENT_WRONG);
		throw new CustomException(Message.SOMETHING_WENT_WRONG);
	}

	@DeleteMapping(path = "/remove/{calanderDateId}")
	public ResponseEntity<?> deleteCalanderDate(@PathVariable("calanderDateId") int calanderDateId) {
		CalenderDate calanderDate = calanderdateService.deleteCalanderDate(calanderDateId);
		if (calanderDate != null) {
			ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), new Date(), false,
					Message.REMOVE_TIMESHEET, calanderDate);
			log.info(Message.REMOVE_TIMESHEET);
			return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		}
		log.error(Message.SOMETHING_WENT_WRONG);
		throw new CustomException(Message.SOMETHING_WENT_WRONG);
	}

	@GetMapping(path = "/get/{calanderDateId}")
	public ResponseEntity<?> getCalanderDate(@PathVariable("calanderDateId") int calanderDateId) {
		CalenderDate calanderDate = calanderdateService.getCalanderDate(calanderDateId);
		if (calanderDate != null) {
			ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), new Date(), false,
					Message.SEARCH_TIMESHEET, calanderDate);
			log.info(Message.SEARCH_TIMESHEET);
			return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		}
		log.error(Message.SOMETHING_WENT_WRONG);
		throw new CustomException(Message.SOMETHING_WENT_WRONG);
	}

	@GetMapping(path = "/get-calander-dates/{timeSheetId}")
	public ResponseEntity<?> getTimeSheet(@PathVariable("timeSheetId") int timeSheetId) {
		TimeSheet timeSheet = calanderdateService.getCalanderDates(timeSheetId);
		if (timeSheet != null) {
			ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), new Date(), false,
					Message.FETCH_ALL_TIMESHEET, timeSheet);
			log.info(Message.FETCH_ALL_TIMESHEET);
			return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		}
		log.error(Message.SOMETHING_WENT_WRONG);
		throw new CustomException(Message.SOMETHING_WENT_WRONG);
	}

}
