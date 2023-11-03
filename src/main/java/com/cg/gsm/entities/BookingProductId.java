package com.cg.gsm.entities;

import java.io.Serializable;

public class BookingProductId implements Serializable {
	
	/*
	 * To achieve the composite key relationship between Product and Booking.
	 */
	private static final long serialVersionUID = 1L;
	private Product product;
	private Booking booking;
	
	/*
	 * Getters and Setters
	 */
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
}
