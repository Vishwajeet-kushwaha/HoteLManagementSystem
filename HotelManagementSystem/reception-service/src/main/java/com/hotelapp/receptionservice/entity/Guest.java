package com.hotelapp.receptionservice.entity;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "guest")
public class Guest {
	
	@Id
	private int guestId;
	
	@Column(nullable = false)
	private String guestName;
	
	@Column(nullable = false)
	private String guestMobile;
	
	@Column(nullable = false)
	private String guestEmail;
	
	@Column(nullable = false)
	private String guestGender;
	
	@Column(nullable = false)
	private String guestAddress;
	
	@Column(nullable = false)
	private String guestGovtId;

	public int getGuestId() {
		return guestId;
	}

	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getGuestMobile() {
		return guestMobile;
	}

	public void setGuestMobile(String guestMobile) {
		this.guestMobile = guestMobile;
	}

	public String getGuestEmail() {
		return guestEmail;
	}

	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}

	public String getGuestGender() {
		return guestGender;
	}

	public void setGuestGender(String guestGender) {
		this.guestGender = guestGender;
	}

	public String getGuestAddress() {
		return guestAddress;
	}

	public void setGuestAddress(String guestAddress) {
		this.guestAddress = guestAddress;
	}

	public String getGuestGovtId() {
		return guestGovtId;
	}

	public void setGuestGovtId(String guestGovtId) {
		this.guestGovtId = guestGovtId;
	}
	
}
