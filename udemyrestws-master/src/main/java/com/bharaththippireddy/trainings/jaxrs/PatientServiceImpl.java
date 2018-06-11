package com.bharaththippireddy.trainings.jaxrs;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

public class PatientServiceImpl implements PatientService {
	private long currentId = 123;
	Map<Long, Patient> patients = new HashMap<Long, Patient>();
	Map<Long, Prescription> prescriptions = new HashMap<Long, Prescription>();

	public PatientServiceImpl() {
		init();
	}

	final void init() {
		Patient patient = new Patient();
		patient.setName("John");
		patient.setId(currentId);
		patients.put(patient.getId(), patient);

		Prescription prescription = new Prescription();
		prescription.setDescription("prescription 223");
		prescription.setId(223);
		prescriptions.put(prescription.getId(), prescription);
	}

	/* will return a Jackson Api response. The response is going to be ok if it returns
	* the patient object. This method adds a patient to the patients Hashmap.
	*/
	public Response addPatient(Patient patient) {
		// logger
		System.out.println("...invoking addPatient, Patient Name is " + patient.getName());
		// set the id, increment current id
		patient.setId(++currentId);
		// add patient to the patients Hashmap
		patients.put(patient.getId(), patient);
		// Return an OK response with the patient object. Build the response.
		return Response.ok(patient).build();
	}

	public Patient getPatient(String id) {
		System.out.println("...invoking getPatient, Patient Id is " + id);
		//parse the id as long when it comes as a request
		Long patientId = Long.parseLong(id);
		// assign to local variable and get the patient by id
		Patient patient = patients.get(patientId);
		// return as xml or json at runtime
		return patient;
	}

	/* Check if the patient is in the map, and then update it.
	 */
	public Response updatePatient(Patient updatedPatient) {
		System.out.println("...invoking updatePatient, Patient Name is " + updatedPatient.getName());
		//extract currentPatient by getting it from the Hashmap by Id 
		Patient currentPatient = patients.get(updatedPatient.getId());
		System.out.println("currentPatient id is " + updatedPatient.getId());
		
		Response response = null;
		// Check to see if the updatedPatient exists in the patients hashmap
		if(currentPatient != null){
			// if it does exist, then update the patient at that id
			patients.put(currentPatient.getId(), updatedPatient);
			// return an ok response if updated
			response = Response.ok(updatedPatient).build();
		} else {
			// tell that we have not modified the patient.
			response = Response.notModified().build();
		}
		
		return response;
	}

	public Response deletePatients(String id) {
		// logger
		System.out.println("...invoking deletePatient, Patient Id is " + id);
		// parse the id from string to long
		Long patientId = Long.parseLong(id);
		// get the patient from the hashmap
		Patient patient = patients.get(patientId);
		Response response = null;
		// if the patient exists in the map, remove the patient. Return okay
		if (patient != null){
			patients.remove(patientId);
			response = Response.ok().build();
		} else {
		// otherwise, return notModified
			response = Response.notModified().build();
		}
		
		return response;
	}

	public Prescription getPrescription(String prescriptionId) {

		return null;
	}

}
