package com.cg.gsm.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Address {
	@Id
	@GeneratedValue(generator = "addrSeq")
	@SequenceGenerator(name = "addrSeq", sequenceName = "Address_Seq", allocationSize = 1)
	private int id;

	@NotBlank(message = "Street is mandatory")
	private String street;

	@NotBlank(message = "Locality is mandatory")
	private String locality;

	@NotBlank(message = "City is mandatory")
	private String city;

	@NotBlank(message = "Pincode is mandatory")
	@Size(min = 6, max = 6, message = "Can be of 6 digits only")
	private String pincode;

	@NotBlank(message = "State is mandatory")
	private String state;

	@NotBlank(message = "Country is mandatory")
	private String country;

	/*
	 * Getters and Setters
	 */
	public Address() {
		super();
	}

	public int getId() {
		return id;
	}

	public String getStreet() {
		return street;
	}

	public String getLocality() {
		return locality;
	}

	public String getCity() {
		return city;
	}

	public String getPincode() {
		return pincode;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
