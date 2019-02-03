package com.surgeri.model.patient;

import lombok.Data;

/**
 * @author amitb
 *
 */
@Data 
public class RecordApiResponse {
	
	private String patientName;
	private int age;
	private String sex;
	private int mobileNumber;

}
