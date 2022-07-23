package com.notable.health.nhtest.data.enums;

/**
 * {@link Enum} to represent an appointment type
 * 
 * @author Hank DeDona
 *
 */
public enum AppointmentType {
	NEW_PATIENT("New Patient"), FOLLOW_UP("Follow Up");

	private String prettyAppointmentTypeText;

	private AppointmentType(String prettyAppointmentTypeText) {
		this.prettyAppointmentTypeText = prettyAppointmentTypeText;
	}

	public String getPrettyAppointmentTypeText() {
		return prettyAppointmentTypeText;
	}
}
