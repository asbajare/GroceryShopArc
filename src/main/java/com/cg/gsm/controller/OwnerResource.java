package com.cg.gsm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.gsm.entities.BookingProduct;
import com.cg.gsm.entities.Product;
import com.cg.gsm.entities.User;
import com.cg.gsm.exception.InvalidInputException;
import com.cg.gsm.exception.ProductNotFoundException;
import com.cg.gsm.exception.UserNotFoundException;
import com.cg.gsm.service.BookingProductServiceInt;
import com.cg.gsm.service.ProductServiceInt;
import com.cg.gsm.service.UserServiceInt;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
public class OwnerResource {
	@Autowired
	private UserServiceInt userserviceint;

	@Autowired
	private ProductServiceInt productserviceint;

	@Autowired
	public BookingProductServiceInt bookingproductserviceint;

	/*
	 * Product Operations
	 */
	// To create a Product
	@ApiOperation(value = "Create a Product", response = Product.class)
	@PostMapping(path = "/owner/product/add")
	public Product createProduct(@Valid @RequestBody Product p, BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			for (FieldError error : errors) {
				throw new InvalidInputException(error.getObjectName() + " - " + error.getDefaultMessage());
			}
		}
		return productserviceint.add(p);
	}

	// To delete a product
	@ApiOperation(value = "Delete Product using Product Code")
	@DeleteMapping(path = "/owner/product/delete/{code}")
	public void deleteProduct(@PathVariable("code") String code) throws ProductNotFoundException {
		productserviceint.deleteByCode(code);
	}

	// To Update Product code by owner
	@ApiOperation(value = "Update Product By Product Code", response = Product.class)
	@PutMapping(path = "/owner/product/update/")
	public Product updateProduct(@Valid @RequestBody Product product, BindingResult bindingResult)
			throws ProductNotFoundException, InvalidInputException {
		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			for (FieldError error : errors) {
				throw new InvalidInputException(error.getObjectName() + " - " + error.getDefaultMessage());
			}
		}
		return productserviceint.updateProduct(product);
	}

	// To get the product with product code
	@ApiOperation(value = "See product by product code", response = Product.class)
	@GetMapping(path = "owner/product/find/{code}")
	public Product retrieveProduct(@PathVariable("code") String code) throws ProductNotFoundException {
		return productserviceint.findByCode(code);
	}

	// To get all Products
	@ApiOperation(value = "See all Products")
	@GetMapping(path = "/owner/product/findAll")
	public ResponseEntity<Object> retreiveAllProducts() {
		List<Product> list = productserviceint.findAll();
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}

	// Owner will get all the users
	@ApiOperation(value = "Get all Users")
	@GetMapping(path = "/owner/users")
	public ResponseEntity<Object> retreiveAllUsers() {
		List<User> list = userserviceint.findAll();
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}

	// To get the user with particular id
	@ApiOperation(value = "Retrieve User by Id", response = User.class)
	@GetMapping(path = "/owner/user/findById/{id}")
	public User retrieveUser(@PathVariable("id") int a) throws UserNotFoundException {

		return userserviceint.findByPK(a);
	}

	// Get all Bookings
	@ApiOperation(value = "Get all Bookings")
	@GetMapping(path = "/owner/bookings/findAll/")
	public ResponseEntity<Object> retrieveallBooking() {

		List<BookingProduct> list = bookingproductserviceint.getUserBooking();
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}

	// To get all the bookings of particular Product
	@ApiOperation(value = "See bookings of specific products")
	@GetMapping(path = "/owner/bookings/findByProduct/{code}")
	public ResponseEntity<Object> retreiveAllBookingByProduct(@PathVariable("code") String s) {
		List<BookingProduct> list = bookingproductserviceint.getProductBooking(s);
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}

}