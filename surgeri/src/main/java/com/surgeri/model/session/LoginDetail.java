package com.surgeri.model.session;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author amitb
 *
 */
@Data
@Entity
@Table(name = "login_details")
public class LoginDetail implements Serializable {
	private static final long serialVersionUID = -488798673349213402L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "is_doctor")
	private int isDoctor;

}
