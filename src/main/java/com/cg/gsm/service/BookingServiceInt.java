package com.cg.gsm.service;

import java.util.List;

import com.cg.gsm.entities.BookingProduct;
import com.cg.gsm.exception.NoOrderBookedException;
import com.cg.gsm.exception.OutofStockException;
import com.cg.gsm.exception.ProductNotFoundException;
import com.cg.gsm.exception.UserNotFoundException;

public interface BookingServiceInt {

	public void add(int id, String[] code, int[] quantity)
			throws ProductNotFoundException, OutofStockException, UserNotFoundException;

	public List<BookingProduct> getUserBookingByID(int a) throws NoOrderBookedException, UserNotFoundException;

}
