package com.surgeri.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surgeri.database.PatientRecordRepository;
import com.surgeri.exception.AuthException;
import com.surgeri.exception.NotFoundException;
import com.surgeri.model.ApiFailureResponse;
import com.surgeri.model.patient.PatientRecord;
import com.surgeri.model.patient.RecordApiRequest;
import com.surgeri.model.patient.RecordApiResponse;
import com.surgeri.model.session.LoginDetail;
import com.surgeri.service.PatientService;
import com.surgeri.util.Constants;

/**
 * @author amitb
 *
 */
@Service
public class PatientServiceImpl implements PatientService {
	
	private static final Logger LOGGER = LogManager.getLogger(PatientServiceImpl.class.getName());
	
	@Autowired
	PatientRecordRepository patientRecordRepo;

	@Override
	public void addPatientRecord(HttpServletRequest request, RecordApiRequest recordApiRequest) {
		if(request.getSession() == null || request.getSession().getAttribute(Constants.LOGGEDIN_USER) == null) {
			throw new AuthException(new ApiFailureResponse(Constants.UNAUTHORIZED));
		}
		LoginDetail loginUserDetails = (LoginDetail) request.getSession().getAttribute(Constants.LOGGEDIN_USER);
		
		PatientRecord patientRecord = new PatientRecord();
		patientRecord.setPatientName(recordApiRequest.getPatientName());
		patientRecord.setAge(recordApiRequest.getAge());
		patientRecord.setSex(recordApiRequest.getSex());
		patientRecord.setMobileNumber(recordApiRequest.getMobileNumber());
		patientRecord.setCreatedBy(loginUserDetails.getUserId());
		
		PatientRecord createdPatientRecord = patientRecordRepo.save(patientRecord);
		LOGGER.info("Patient record : {} added successfully", createdPatientRecord.getPatientId());
	}

	@Override
	public List<RecordApiResponse> getPatientRecords(HttpServletRequest request) {
		if(request.getSession() == null || request.getSession().getAttribute(Constants.LOGGEDIN_USER) == null) {
			throw new AuthException(new ApiFailureResponse(Constants.UNAUTHORIZED));
		}
		
		List<RecordApiResponse> patientRecords = new ArrayList<>();
		LoginDetail loginUserDetails = (LoginDetail) request.getSession().getAttribute(Constants.LOGGEDIN_USER);
		LOGGER.info("Fetching patient records for user: {}", loginUserDetails.getUserName());
		//check if doctor
		if(loginUserDetails.getIsDoctor() == 1) {
			patientRecordRepo.findAll().forEach(e -> addToList(e, patientRecords));
		} else {
			patientRecordRepo.findByCreatedBy(loginUserDetails.getUserId()).forEach(e -> addToList(e, patientRecords));
		}
		
		if(patientRecords.size() == 0) {
			throw new NotFoundException(new ApiFailureResponse(Constants.NOT_FOUND));
		}
		
		return patientRecords;
	}
	
	private void addToList(PatientRecord patientRecord, List<RecordApiResponse> patientRecords) {
		RecordApiResponse recordApiResponse = new RecordApiResponse();
		recordApiResponse.setPatientName(patientRecord.getPatientName());
		recordApiResponse.setAge(patientRecord.getAge());
		recordApiResponse.setSex(patientRecord.getSex());
		recordApiResponse.setMobileNumber(patientRecord.getMobileNumber());
		
		patientRecords.add(recordApiResponse);
	}

}
