package com.cg.gsm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.gsm.entities.Booking;
import com.cg.gsm.entities.BookingProduct;
import com.cg.gsm.entities.Product;
import com.cg.gsm.exception.NoOrderBookedException;
import com.cg.gsm.exception.OutofStockException;
import com.cg.gsm.exception.ProductNotFoundException;
import com.cg.gsm.exception.UserNotFoundException;
import com.cg.gsm.repository.BookingDAOInt;
import com.cg.gsm.repository.BookingProductDAOInt;

@Service
public class BookingServiceIntImp implements BookingServiceInt {

	@Autowired
	private BookingDAOInt bookdaoint;

	@Autowired
	private BookingProductDAOInt bookingproductdaoint;

	@Autowired
	private ProductServiceInt productserviceint;

	@Autowired
	private UserServiceInt userserviceint;

	
	//To add Booking
	@Override
	public void add(int id, String[] code, int[] quantity)
			throws ProductNotFoundException, OutofStockException, UserNotFoundException {

		Booking booking = new Booking();
		List<BookingProduct> listbp = new ArrayList<BookingProduct>();
		Product p = null;
		double amount = 0;
		for (int i = 0; i < quantity.length; i++) {
			p = productserviceint.findByCode(code[i]);
			if (p == null)
				throw new ProductNotFoundException("Product with code = " + code[i] + " Not Found");
			if (p.getQuantityInStock() < quantity[i]) {
				throw new OutofStockException(
						"Product with code = " + code[i] + " is Out of Stock..Cannot Book Product!!!!");
			}
			amount = amount + p.getPrice() * quantity[i];
			BookingProduct bp = new BookingProduct();
			bp.setProduct(p);
			p.setQuantityInStock(p.getQuantityInStock() - quantity[i]);
			bp.setQuantity(quantity[i]);
			bp.setBooking(booking);
			listbp.add(bp);
		}
		booking.setAmount(amount);
		booking.setBookingproducts(listbp);
		booking.setUser(userserviceint.findByPK(id));
		bookdaoint.save(booking);
	}
    //To Get User Booking
	@Override
	public List<BookingProduct> getUserBookingByID(int a) throws NoOrderBookedException, UserNotFoundException {
		userserviceint.findByPK(a);
		List<BookingProduct> bp = bookingproductdaoint.getUserBookingById(a);

		if (bp.isEmpty()) {
			throw new NoOrderBookedException("User with id " + a + " have not booked any product");
		}
		return bp;
	}
}
