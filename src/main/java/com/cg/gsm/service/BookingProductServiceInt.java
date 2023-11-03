package com.cg.gsm.service;

import java.util.List;

import com.cg.gsm.entities.BookingProduct;

public interface BookingProductServiceInt {

	public List<BookingProduct> getUserBooking();

	public List<BookingProduct> getProductBooking(String code);

	public List<BookingProduct> getProductBooking5();

}
