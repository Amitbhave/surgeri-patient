package com.surgeri.model.patient;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author amitb
 *
 */
@Data
public class RecordApiRequest {
	
	@NotNull
	private String patientName;
	@NotNull
	private int age;
	@NotNull
	private String sex;
	@NotNull
	private int mobileNumber;

}
