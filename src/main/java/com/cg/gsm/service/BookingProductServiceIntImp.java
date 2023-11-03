package com.cg.gsm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.gsm.entities.BookingProduct;
import com.cg.gsm.repository.BookingProductDAOInt;

@Service
public class BookingProductServiceIntImp implements BookingProductServiceInt {

	@Autowired
	private BookingProductDAOInt bookingproductdaoint;

    
	//To get User Booking
	@Override
	public List<BookingProduct> getUserBooking() {

		List<BookingProduct> listofbp = bookingproductdaoint.findAll();
		return listofbp;
	}
    //To get Product Booking
	@Override
	public List<BookingProduct> getProductBooking(String code) {
		List<BookingProduct> listofbp = bookingproductdaoint.getProductBookingById(code);
		return listofbp;
	}
	
	@Override
	public List<BookingProduct> getProductBooking5() {
		List<BookingProduct> listofbp = bookingproductdaoint.getProductBookingwhosequantityisgreaterthan5();
		return listofbp;
	}

}
