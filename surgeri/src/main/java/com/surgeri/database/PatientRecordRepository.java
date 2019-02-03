/**
 * 
 */
package com.surgeri.database;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.surgeri.model.patient.PatientRecord;

/**
 * @author amitb
 *
 */
public interface PatientRecordRepository extends CrudRepository<PatientRecord, Integer> {
	
	List<PatientRecord> findByCreatedBy(int createdBy);

}
