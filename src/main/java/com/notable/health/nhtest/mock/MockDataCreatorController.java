package com.notable.health.nhtest.mock;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.notable.health.nhtest.data.enums.AppointmentType;
import com.notable.health.nhtest.data.models.Appointment;
import com.notable.health.nhtest.data.models.Doctor;
import com.notable.health.nhtest.data.models.Patient;
import com.notable.health.nhtest.data.repositories.DoctorRepository;

/**
 * {@link Controller} to create mock data on the fly!
 * 
 * @author Hank DeDona
 *
 */
@Controller()
@RequestMapping("/mock")
public class MockDataCreatorController {

	@GetMapping
	@ResponseBody
	public String good() {
		Doctor dr = new Doctor();
		dr.setFirstName("Billy");
		dr.setLastName("Jones");

		dr.addAppointment(new Appointment(new Patient("New", "Sick"), AppointmentType.NEW_PATIENT,
				LocalDateTime.of(2022, 7, 25, 9, 30), 30));

		DoctorRepository.saveDoctor(dr);

		Doctor dr2 = new Doctor();
		dr2.setFirstName("Adam");
		dr2.setLastName("Smith");

		dr2.addAppointment(new Appointment(new Patient("Followup", "RealSick"), AppointmentType.FOLLOW_UP,
				LocalDateTime.of(2022, 7, 25, 11, 15), 15));

		DoctorRepository.saveDoctor(dr2);

		Doctor dr3 = new Doctor();
		dr3.setFirstName("Jane");
		dr3.setLastName("Doe");

		dr3.addAppointment(new Appointment(new Patient("Followup", "KindaSick"), AppointmentType.FOLLOW_UP,
				LocalDateTime.of(2022, 7, 25, 15, 45), 15));

		dr3.addAppointment(new Appointment(new Patient("NewPatient", "Bleh"), AppointmentType.NEW_PATIENT,
				LocalDateTime.of(2022, 7, 25, 17, 45), 30));

		dr3.addAppointment(new Appointment(new Patient("Followup", "Face"), AppointmentType.FOLLOW_UP,
				LocalDateTime.of(2022, 7, 25, 9, 00), 15));

		dr3.addAppointment(new Appointment(new Patient("Followup", "SuperSick"), AppointmentType.FOLLOW_UP,
				LocalDateTime.of(2022, 7, 27, 16, 00), 60));

		DoctorRepository.saveDoctor(dr3);

		return "Created mock data successfully!";
	}

	@GetMapping
	@ResponseBody
	@RequestMapping("/bad")
	public String bad() {

		// Try to add 4 appointments at same date/time
//		Doctor dr = new Doctor();
//		dr.setFirstName("Billy");
//		dr.setLastName("Jones");
//
//		dr.addAppointment(new Appointment(new Patient("Patient", "One"), AppointmentType.NEW_PATIENT,
//				LocalDateTime.of(2022, 7, 25, 9, 30), 30));
//		dr.addAppointment(new Appointment(new Patient("Patient", "Two"), AppointmentType.NEW_PATIENT,
//				LocalDateTime.of(2022, 7, 25, 9, 30), 30));
//		dr.addAppointment(new Appointment(new Patient("Patient", "Three"), AppointmentType.NEW_PATIENT,
//				LocalDateTime.of(2022, 7, 25, 9, 30), 30));
//		dr.addAppointment(new Appointment(new Patient("Patient", "Four"), AppointmentType.NEW_PATIENT,
//				LocalDateTime.of(2022, 7, 25, 9, 30), 30));

		// Try to add appointment at 9:20 (not on 15s)
//		Doctor dr = new Doctor();
//		dr.setFirstName("Billy");
//		dr.setLastName("Jones");
//
//		dr.addAppointment(new Appointment(new Patient("Patient", "One"), AppointmentType.NEW_PATIENT,
//				LocalDateTime.of(2022, 7, 25, 9, 20), 40));

		return "Created mock data successfully!";

	}

}
