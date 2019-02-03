package com.surgeri.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.surgeri.model.patient.RecordApiRequest;
import com.surgeri.model.patient.RecordApiResponse;

/**
 * @author amitb
 *
 */
public interface PatientService {
	
	void addPatientRecord(HttpServletRequest request, RecordApiRequest recordApiRequest);
	List<RecordApiResponse> getPatientRecords(HttpServletRequest request);

}
