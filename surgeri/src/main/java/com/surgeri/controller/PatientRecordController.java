package com.surgeri.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.surgeri.model.patient.RecordApiRequest;
import com.surgeri.model.patient.RecordApiResponse;
import com.surgeri.service.PatientService;

/**
 * @author amitb
 *
 */
@RestController
@RequestMapping(value = "/patient")
public class PatientRecordController {
	
	@Autowired
	PatientService patientService;
	
	@GetMapping("/getAll")
	public ResponseEntity<List<RecordApiResponse>> getPatientDetails(HttpServletRequest request){
		return new ResponseEntity<>(patientService.getPatientRecords(request), HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> savePatientDetails(HttpServletRequest request, @RequestBody @Valid RecordApiRequest recordApiRequest){
		patientService.addPatientRecord(request, recordApiRequest);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
