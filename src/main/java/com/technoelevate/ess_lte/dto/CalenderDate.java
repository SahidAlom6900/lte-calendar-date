package com.technoelevate.ess_lte.dto;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Calander_Date")
@JsonIgnoreProperties({"timeSheet"})
public class CalenderDate implements Serializable {
	@Id
	@SequenceGenerator(name = "calander_date_sequence_generator", initialValue = 100, allocationSize = 1)
	@GeneratedValue(generator = "calander_date_sequence_generator")
	@Column(name = "calander_date_id")
	private int calanderDateId;

	@NotNull(message = "Day Message Can Not be Null")
	@NotEmpty(message = "Day Message Can Not be Empty")
	private String dayMessage;

	@NotNull(message = "Login Time Can Not Be Null")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Kolkata")
	private LocalTime loginTime;

	@NotNull(message = "Logout Time Can Not Be Null")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Kolkata")
	private LocalTime logoutTime;
	
	@NotNull(message = "Logout Time Can Not Be Null")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private Date date;

	@NotNull(message = "Daily Task Description Can Not be Null")
	@NotEmpty(message = "Daily Task Description Can Not be Empty")
	private String dailyTaskDetails;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "time_sheet_id")
	private TimeSheet timeSheet;

}
