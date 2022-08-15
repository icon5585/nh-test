package com.notable.health.nhtest.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.notable.health.nhtest.data.models.Appointment;
import com.notable.health.nhtest.data.models.AppointmentRequest;
import com.notable.health.nhtest.data.models.Doctor;
import com.notable.health.nhtest.data.repositories.DoctorRepository;

/**
 * {@link RestController} for {@link Doctor}s including {@link Appointment}s
 * 
 * @author Hank DeDona
 */
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

	/**
	 * Get all of the doctors and their respective information and appointments
	 * 
	 * @return a {@link List} of {@link Doctor}s and their {@link Appointment}s
	 * @throws ResponseStatusException if no {@link Doctor}s are found
	 */
	@GetMapping
	public List<Doctor> getAllDoctors() {

		Map<Integer, Doctor> doctors = DoctorRepository.getAllDoctors();

		if (doctors.values().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No doctors found!");
		}

		return new ArrayList<Doctor>(doctors.values());
	}

	/**
	 * Get all information on a specific {@link Doctor} provided an id
	 * 
	 * @param id the ID to look up the {@link Doctor} by
	 * @return the {@link Doctor} object if found
	 * @throws ResponseStatusException if no {@link Doctor} is found
	 */
	@GetMapping
	@RequestMapping("/{id}")
	public Doctor getDoctorById(@PathVariable Integer id, @RequestHeader("X-User-Id") String userId,
			@RequestHeader("X-User-Role") String userRole) {
		// Authentication here
		if(userRole.equalsIgnoreCase("admin")) {
			return getDoctor(id);
		} else if (userRole.equalsIgnoreCase("doctor")) {
			// Check
			if(!Integer.valueOf(userId).equals(id)) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized!");
			}
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unknown role!");
		}
		
		return getDoctor(id);
	}

	// Update an appointment
	// appointment_id
	// patient first name
	// patient last name
	// appointment type
	// when it starts (start date time)
	// duration

	/**
	 * 
	 */
	@PutMapping
	@RequestMapping(value = "/{id}/appointments/{appointmentId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public String updateDoctorAppointmentById(@PathVariable Integer id, @PathVariable Integer appointmentId,
			@ModelAttribute @Valid AppointmentRequest appointmentRequest) {
		// Find doctor
		Doctor doctorToFind = getDoctor(id);

		// Find existing appointment
		Appointment appt = doctorToFind.getAppointmentById(appointmentId);
		if (appt == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("Appointment with ID %d not found", appointmentId));
		}

		// Should have existing appointment here
		// Delete existing appointment
		doctorToFind.deleteAppointmentById(appointmentId);

		// Should I just pass in my "new" appointment request object?
		appt = new Appointment(appointmentRequest, appointmentId);

		// Add appt back to doctor
		doctorToFind.addAppointment(appt);

		// Save it
		DoctorRepository.saveDoctor(doctorToFind);

		// How to update doctor? Decouple appointments from doctors

		return String.format("Successfully updated appointment with ID %d", appointmentId);
	}

	// TODO /appointments for all appoinments for a given doctor

	/**
	 * Get all {@link Appointment}s for a specific {@link Doctor} provided their id,
	 * and year (yyyy), month (mm), and day (dd)
	 * 
	 * @param id    the ID to look up the {@link Doctor} by
	 * @param year  4-digit year
	 * @param month 2-digit month
	 * @param day   2-digit day
	 * @return {@link List} of {@link Appointment}s for that {@link Doctor} if found
	 * @throws ResponseStatusException if no {@link Doctor} or matching
	 *                                 {@link Appointment} is found
	 */
	@GetMapping
	@RequestMapping("/{id}/appointments/{year}/{month}/{day}")
	public List<Appointment> getDoctorAppointmentsByIdAndDate(@PathVariable Integer id, @PathVariable Integer year,
			@PathVariable Integer month, @PathVariable Integer day) {
		// TODO: INPUT VALIDATION!!! MAKE SURE THAT THE YEAR IS 4 DIGITS (WITHIN REASON
		// OF 2022 MAYBE?), MONTH 2 DIGITS BETWEEN 1-12, AND DAY IS 2 DIGITS BETWEEN
		// 1-31 (NOT WORRYING ABOUT 30 AND 28/29 DAY MONTHS RIGHT NOW!)

		// Find doctor
		Doctor doctorToFind = getDoctor(id);

		// Create LocalDateTime object based on yyyy-mm-dd
		// Note: Since we're getting ALL appointments for a given year/month/day, hours
		// & minutes don't matter
		List<Appointment> matchingAppointments = doctorToFind
				.getAppointmentsByDate(LocalDateTime.of(year, month, day, 0, 0));

		if (matchingAppointments.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(
					"No matching appointments for doctor with ID %d and for date %d-%d-%d", id, year, month, day));
		}

		return matchingAppointments;
	}

	/**
	 * Delete a specific {@link Appointment} for a given {@link Doctor} provided
	 * their appropriate IDs
	 * 
	 * @param id            the ID to look up the {@link Doctor} by
	 * @param appointmentId the ID to look up the {@link Appointment} by
	 * @return success message if successful
	 * @throws ResponseStatusException if no {@link Doctor} or matching
	 *                                 {@link Appointment} is found
	 */
	@DeleteMapping
	@RequestMapping(value = "/{id}/appointments/{appointmentId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public String deleteDoctorAppointmentById(@PathVariable Integer id, @PathVariable Integer appointmentId) {
		// Find doctor
		Doctor doctorToFind = getDoctor(id);

		if (!doctorToFind.deleteAppointmentById(appointmentId)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String
					.format("No matching appointments for doctor with ID %d and appointment ID %d", id, appointmentId));
		}

		return String.format("Successfully deleted appointment with ID %d", appointmentId);
	}

	@PostMapping
	@RequestMapping("/{id}/appointments")
	@ResponseStatus(HttpStatus.CREATED)
	public String addAppointmentById(@PathVariable Integer id,
			@ModelAttribute @Valid AppointmentRequest appointmentRequest, Errors errors) {
		Doctor doctorToAddAppt = getDoctor(id);

		Appointment apptToAdd = new Appointment(appointmentRequest);

		doctorToAddAppt.addAppointment(apptToAdd);

		return String.format("Successfully created appointment an appointment for doctor with ID %d", id);
	}

	/**
	 * Private method to fetch a {@link Doctor} provided an id @param id the
	 * {@link Doctor} ID to look up @return the {@link Doctor} if found @throws
	 */
	private Doctor getDoctor(Integer id) {
		Doctor doctorToFetch = DoctorRepository.getDoctorById(id);

		if (doctorToFetch == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Doctor with ID %d not found", id));
		}

		return doctorToFetch;
	}

}
