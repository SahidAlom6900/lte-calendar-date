package com.technoelevate.ess_lte.dto;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheet implements Serializable {

	@Id
	@SequenceGenerator(name = "time_sheet_sequence_generator", initialValue = 100, allocationSize = 1)
	@GeneratedValue(generator = "time_sheet_sequence_generator")
	@Column(name = "time_sheet_id")
	private int timeSheetId;

	@NotNull(message = "Project Name Can Not be Null")
	@NotEmpty(message = "Project Name Can Not be Empty")
	private String projectName;

	@Email(message = "Enter the Proper Email Id")
	private String teEmailId;

	@Email(message = "Enter the Proper Email Id")
	private String clientEmailId;

	@NotNull(message = "Month Can Not Be Null")
	@NotEmpty(message = "Month Can Not be Empty")
	private String month;

	@NotNull(message = "Login Time Can Not Be Null")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Kolkata")
	private LocalTime loginTime;

	@NotNull(message = "Logout Time Can Not Be Null")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Kolkata")
	private LocalTime logoutTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Kolkata")
	private LocalTime totalWorkingTime;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "person_id")
	@Valid
	private Person person;
	
	@OneToMany(mappedBy = "timeSheet",fetch = FetchType.EAGER)
	private List<CalenderDate> calanderDates;

	@Override
	public String toString() {
		return "TimeSheet [timeSheetId=" + timeSheetId + ", projectName=" + projectName + ", teEmailId=" + teEmailId
				+ ", clientEmailId=" + clientEmailId + ", month=" + month + ", loginTime=" + loginTime + ", logoutTime="
				+ logoutTime + ", totalWorkingTime=" + totalWorkingTime + "]";
	}
	
}
