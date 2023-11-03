package com.cg.gsm.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id
	@GeneratedValue(generator = "userseq")
	@SequenceGenerator(name = "userseq", sequenceName = "User_Seq", allocationSize = 1)
	private int id;

	@NotBlank(message = "First name is mandatory")
	@Pattern(regexp = "[a-zA-Z]*", message = "First name should be alphabets only")
	private String firstName;

	@NotBlank(message = "Last name is mandatory")
	@Pattern(regexp = "[a-zA-Z]*", message = "Last name should be alphabets only")
	private String lastName;

	@NotBlank(message = "Mobile No is mandatory")
	@Size(min = 10, max = 10, message = "Mobile No can be of 10 number only")
	@Pattern(regexp = "^[7-9]{1}\\d{9}$", message = "Mobile No can be of 10 number only")
	private String mobileNo;

	@NotBlank(message = "Email is mandatory")
	@Email
	private String emailId;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "add_id")
	private Address address;

	@JsonIgnore
	@OneToMany(targetEntity = Booking.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public List<Booking> bookings;

	/*
	 * Constructors
	 */
	public User() {
		super();
	}

	public User(int id, @NotBlank(message = "First name is mandatory") String firstName,
			@NotBlank(message = "Last name is mandatory") String lastName,
			@NotBlank(message = "Mobile No is mandatory") @Size(min = 10, max = 10, message = "Mobile No can be of 10 number only") @Pattern(regexp = "^[7-9]{1}\\d{9}$", message = "Mobile No can be of 10 number only") String mobileNo,
			@NotBlank(message = "Email is mandatory") @Email String emailId, Address address, List<Booking> bookings) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.address = address;
		this.bookings = bookings;
	}

	/*
	 * Getter & setters for User
	 */
	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public Address getAddress() {
		return address;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

}
