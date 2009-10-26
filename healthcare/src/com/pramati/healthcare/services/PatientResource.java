package com.pramati.healthcare.services;

import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.pramati.healthcare.model.Patient;

@Path("/patients")
public class PatientResource{
	private Map<Integer, Patient> patientDB = new ConcurrentHashMap<Integer, Patient>();
	private AtomicInteger idCounter = new AtomicInteger();
	private Logger logger = Logger.getLogger(PatientResource.class);

	public PatientResource() {
		/*
		 * do nothing
		 */
	}

	@POST
	@Consumes("application/xml")
	public Response createPatient(InputStream is) {
		Patient patient = readInputStream(is);
		patient.setPatientId(idCounter.incrementAndGet());
		patientDB.put(patient.getPatientId(), patient);
		logger.info("Created patient " + patient.getPatientId());
		return Response.created(
				URI.create("/patients" + patient.getPatientId())).build();
	}

	private Patient readInputStream(InputStream is) {
		return null;
	}

}
