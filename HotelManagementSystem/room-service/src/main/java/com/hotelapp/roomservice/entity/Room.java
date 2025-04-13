package com.hotelapp.roomservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "room")
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roomId;
	
	@Column(nullable = false, unique = true)
	private int roomNo;
	
	@Column(nullable = false)
	private String roomType;
	
	@Column(nullable = false)
	private double roomPrice;
	
	@Column(nullable = false)
	private boolean roomAvail;
	
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
	public double getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}
	public boolean isRoomAvail() {
		return roomAvail;
	}
	public void setRoomAvail(boolean roomAvail) {
		this.roomAvail = roomAvail;
	}
	
}
