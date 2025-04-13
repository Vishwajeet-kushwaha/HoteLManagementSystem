package com.hotel.inventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Inventory")
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	 private Long id;

	    private String type;       
	    private String name;       
	    private boolean isAvailable;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public boolean isAvailable() {
			return isAvailable;
		}
		public void setAvailable(boolean isAvailable) {
			this.isAvailable = isAvailable;
		}
		public Inventory(Long id, String type, String name, boolean isAvailable) {
			super();
			this.id = id;
			this.type = type;
			this.name = name;
			this.isAvailable = isAvailable;
		}
		public Inventory() {
			super();
			// TODO Auto-generated constructor stub
		}
	
}
