package com.notable.health.nhtest.data.repositories;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.notable.health.nhtest.data.models.Doctor;

/**
 * Fake CRUD repository for {@link Doctor} objects
 * 
 * @author Hank DeDona
 */
public class DoctorRepository {
	
	private static Map<Integer, Doctor> doctorMap = new ConcurrentHashMap<Integer, Doctor>();
	
	public static Map<Integer, Doctor> getAllDoctors() {
		return doctorMap;
	}
	
	public static void saveDoctor(Doctor doctor) {
		// There are much better ways/frameworks to do input validation!
		assert(doctor != null);
		
		Doctor previousDoctorStored = doctorMap.putIfAbsent(doctor.getId(), doctor);
		
		if(previousDoctorStored != null) {
			// The doctor already exists, update?
		}
	}
	
	public static Doctor getDoctorById(Integer id) {
		assert(id != null && id > 0);
		
		return doctorMap.get(id);
	}
	
	public static void updateDoctor(Doctor doctor) {
		assert(doctor != null);
		
		doctorMap.put(doctor.getId(), doctor);
	}

}
