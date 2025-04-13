package com.hotelapp.receptionservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Receptionist {

	@Id
	private int receptionistId;
	private String receptionistName;
	private String receptionistMobile;
	private String receptionistEmail;
	private String userName;
	private String password;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getReceptionistId() {
		return receptionistId;
	}
	public void setReceptionistId(int receptionistId) {
		this.receptionistId = receptionistId;
	}
	public String getReceptionistName() {
		return receptionistName;
	}
	public void setReceptionistName(String receptionistName) {
		this.receptionistName = receptionistName;
	}
	public String getReceptionistMobile() {
		return receptionistMobile;
	}
	public void setReceptionistMobile(String receptionistMobile) {
		this.receptionistMobile = receptionistMobile;
	}
	public String getReceptionistEmail() {
		return receptionistEmail;
	}
	public void setReceptionistEmail(String receptionistEmail) {
		this.receptionistEmail = receptionistEmail;
	}
	
	
}
