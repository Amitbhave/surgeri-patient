package com.surgeri.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.surgeri.model.session.LoginDetail;

/**
 * @author amitb
 *
 */
@Repository
public interface UserDetailsRepository extends CrudRepository<LoginDetail, Integer>{
	
	public LoginDetail findByUserNameAndPassword(String userName, String password);

}
