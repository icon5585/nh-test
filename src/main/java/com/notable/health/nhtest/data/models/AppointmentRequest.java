package com.notable.health.nhtest.data.models;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.notable.health.nhtest.data.enums.AppointmentType;

public class AppointmentRequest {

	@Valid
	@NotBlank
	@Size(min = 2, max = 50, message = "Patient first name must be between 2 and 50 characters")
	private String patientFirstName;

	@Valid
	@NotBlank
	@Size(min = 2, max = 50, message = "Patient last name must be between 2 and 50 characters")
	private String patientLastName;

	@Valid
	@NotNull
	// Note: I would make this value configurable based on the current system clock
	// year!
	@Min(value = 2022, message = "Year must be in the year 2022")
	@Max(value = 2025, message = "Year cannot be further than the year 2025")
	private Integer year;

	@Valid
	@NotNull
	@Min(value = 1, message = "Month must be between 1 and 12 for January through Decemeber")
	@Max(value = 12, message = "Month must be between 1 and 12 for January through Decemeber")
	private Integer month;

	@Valid
	@NotNull
	// Note: I'm aware that there are months with 30 and 28/29 days, would fix this
	// with more time/research!
	@Min(value = 1, message = "Day must be between 1 and 31")
	@Max(value = 31, message = "Day must be between 1 and 31")
	private Integer day;

	@Valid
	@NotNull
	@Min(value = 0, message = "Hour must be between 0 and 23")
	@Max(value = 23, message = "Hour must be between 0 and 23")
	private Integer hour;

	@Valid
	@NotNull
	@Min(value = 0, message = "Minute must be between 0 and 59")
	@Max(value = 59, message = "Minute must be between 0 and 59")
	private Integer minute;

	@Valid
	@NotNull
	@Min(value = 15, message = "Appointment duration must be between 15 and 360 minutes")
	@Max(value = 360, message = "Appointment duration must be between 15 and 360 minutes")
	private Integer durationInMinutes;

	@Valid
	@NotNull
	private AppointmentType appointmentType;

	
	
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	public String getPatientFirstName() {
		return patientFirstName;
	}

	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	public String getPatientLastName() {
		return patientLastName;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getYear() {
		return year;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getMonth() {
		return month;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getDay() {
		return day;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getHour() {
		return hour;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setDurationInMinutes(Integer durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}

	public Integer getDurationInMinutes() {
		return durationInMinutes;
	}

	public void setAppointmentType(AppointmentType appointmentType) {
		this.appointmentType = appointmentType;
	}

	public AppointmentType getAppointmentType() {
		return appointmentType;
	}
}
