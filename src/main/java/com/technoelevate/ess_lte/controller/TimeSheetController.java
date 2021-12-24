package com.technoelevate.ess_lte.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technoelevate.ess_lte.dto.TimeSheet;
import com.technoelevate.ess_lte.exception.CustomException;
import com.technoelevate.ess_lte.response.Message;
import com.technoelevate.ess_lte.response.ResponseMessage;
import com.technoelevate.ess_lte.service.TimeSheetService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@RequestMapping("/api/v1/timesheet")
public class TimeSheetController {
	@Autowired
	private TimeSheetService timeSheetService;

	@PostMapping(path = "/add")
	public ResponseEntity<?> saveTimeSheet(@Valid @RequestBody TimeSheet timeSheet) {
		TimeSheet saveTimeSheet = timeSheetService.saveTimeSheet(timeSheet);
		if (saveTimeSheet != null) {
			ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), new Date(), false,
					Message.DATA_ADDED, saveTimeSheet);
			log.info(Message.DATA_ADDED);
			return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		}
		log.error(Message.SOMETHING_WENT_WRONG);
		throw new CustomException(Message.SOMETHING_WENT_WRONG);
	}

	@DeleteMapping(path = "/remove/{timeSheetId}")
	public ResponseEntity<?> deleteTimeSheet(@PathVariable("timeSheetId") int timeSheetId) {
		TimeSheet saveTimeSheet = timeSheetService.deleteTimeSheet(timeSheetId);
		if (saveTimeSheet != null) {
			ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), new Date(), false,
					Message.REMOVE_TIMESHEET, saveTimeSheet);
			log.info(Message.REMOVE_TIMESHEET);
			return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		}
		log.error(Message.SOMETHING_WENT_WRONG);
		throw new CustomException(Message.SOMETHING_WENT_WRONG);
	}

	@GetMapping(path = "/get/{timeSheetId}")
	public ResponseEntity<?> getTimeSheet(@PathVariable("timeSheetId") int timeSheetId) {
		TimeSheet saveTimeSheet = timeSheetService.getTimeSheet(timeSheetId);
		if (saveTimeSheet != null) {
			ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), new Date(), false,
					Message.SEARCH_TIMESHEET, saveTimeSheet);
			log.info(Message.SEARCH_TIMESHEET);
			return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		}
		log.error(Message.SOMETHING_WENT_WRONG);
		throw new CustomException(Message.SOMETHING_WENT_WRONG);
	}

	@PutMapping(path = "/edit")
	public ResponseEntity<?> editTimeSheet(@Valid @RequestBody TimeSheet timeSheet) {
		TimeSheet saveTimeSheet = timeSheetService.saveTimeSheet(timeSheet);
		if (saveTimeSheet != null) {
			ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), new Date(), false,
					Message.EDIT_TIMESHEET, saveTimeSheet);
			log.info(Message.EDIT_TIMESHEET);
			return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		}
		log.error(Message.SOMETHING_WENT_WRONG);
		throw new CustomException(Message.SOMETHING_WENT_WRONG);
	}

	@GetMapping(path = "/get-all/{personId}")
	public ResponseEntity<?> getAllTimeSheet(@PathVariable("personId") int personId) {
		 List<TimeSheet> allTimeSheet = timeSheetService.getAllTimeSheet(personId);
		if (!allTimeSheet.isEmpty()) {
			ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), new Date(), false,
					Message.FETCH_ALL_TIMESHEET, allTimeSheet);
			log.info(Message.FETCH_ALL_TIMESHEET);
			return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
		}
		log.error(Message.SOMETHING_WENT_WRONG);
		throw new CustomException(Message.SOMETHING_WENT_WRONG);
	}

}
