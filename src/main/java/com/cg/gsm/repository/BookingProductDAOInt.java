package com.cg.gsm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.gsm.entities.BookingProduct;

public interface BookingProductDAOInt extends JpaRepository<BookingProduct, Integer> {

	/*
	 * Custom Query
	 */
	@Query(value = "from BookingProduct where Booking_id in (select b.id from Booking b where user.id= ?1)")
	public List<BookingProduct> getUserBookingById(int a);

	@Query(value = "from BookingProduct where Product_code in (select p.id from Product p where p.id=?1)")
	public List<BookingProduct> getProductBookingById(String code);
	
    @Query(value = "from BookingProduct b where b.quantity>5")
 	public List<BookingProduct> getProductBookingwhosequantityisgreaterthan5();

}
