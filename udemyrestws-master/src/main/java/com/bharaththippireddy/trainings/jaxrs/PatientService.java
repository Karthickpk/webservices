package com.bharaththippireddy.trainings.jaxrs;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Produces({"application/xml", "application/json"})
public interface PatientService {

	// Annotate the HTTP requests and the Paths from JAX-RS
	@GET
	@Path("/patients/{id}/")
	//inject id
	Patient getPatient(@PathParam("id") String id);
	
	@PUT
	@Path("/patients/")
	Response updatePatient(Patient updatedPatient);
	
	@POST
	@Path("/patients/")
	Response addPatient(Patient patient);
	
	@DELETE
	@Path("/patients/{id}/")
	Response deletePatients(@PathParam("id") String id);

	Prescription getPrescription(String prescriptionId);

}