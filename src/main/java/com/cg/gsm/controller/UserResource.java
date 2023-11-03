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
import com.cg.gsm.entities.Address;
import com.cg.gsm.entities.BookingProduct;
import com.cg.gsm.entities.Product;
import com.cg.gsm.entities.User;
import com.cg.gsm.exception.InvalidInputException;
import com.cg.gsm.exception.NoOrderBookedException;
import com.cg.gsm.exception.OutofStockException;
import com.cg.gsm.exception.ProductNotFoundException;
import com.cg.gsm.exception.UserNotFoundException;
import com.cg.gsm.service.BookingProductServiceInt;
import com.cg.gsm.service.BookingServiceInt;
import com.cg.gsm.service.ProductServiceInt;
import com.cg.gsm.service.UserServiceInt;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
public class UserResource {

	@Autowired
	private UserServiceInt userserviceint;

	@Autowired
	private ProductServiceInt productserviceint;

	@Autowired
	public BookingServiceInt bookingserviceint;

	@Autowired
	public BookingProductServiceInt bookingproductserviceint;

	/*
	 * User Operation
	 */
	// To create User
	@ApiOperation(value = "Create a User", response = User.class)
	@PostMapping(path = "/user/add")
	public User createUser(@Valid @RequestBody User user, BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			for (FieldError error : errors) {
				throw new InvalidInputException(error.getObjectName() + " - " + error.getDefaultMessage());
			}
		}
		return userserviceint.add(user);
	}

	// To delete a User
	@ApiOperation(value = "Delete User by Id")
	@DeleteMapping(path = "/user/delete/{id}")
	public void deleteUser(@PathVariable("id") int a) throws UserNotFoundException {
		userserviceint.deleteById(a);
	}

	// To update User
	@ApiOperation(value = "Update user details", response = User.class)
	@PutMapping(path = "/user/update/")
	public User updateUser(@Valid @RequestBody User user, BindingResult bindingResult)
			throws UserNotFoundException, InvalidInputException {
		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			for (FieldError error : errors) {
				throw new InvalidInputException(error.getObjectName() + " - " + error.getDefaultMessage());
			}
		}
		return userserviceint.updateUser(user);
	}

	// To Update User's Address
	@ApiOperation(value = "Update User address by Id", response = Address.class)
	@PutMapping(path = "/user/address/update/{id}")
	public Address updateUserAddress(@PathVariable("id") int a, @Valid @RequestBody Address address,
			BindingResult bindingResult) throws UserNotFoundException, InvalidInputException {

		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			for (FieldError error : errors) {
				throw new InvalidInputException(error.getObjectName() + " - " + error.getDefaultMessage());
			}
		}
		return userserviceint.updateAdd(a, address);
	}

	/*
	 * Get the products
	 */
	@ApiOperation(value = "See all products to buy")
	@GetMapping(path = "/user/product/findAll")
	public ResponseEntity<Object> retreiveAllProducts() {
		List<Product> list = productserviceint.findAll();
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}

	// To Book a product
	@ApiOperation(value = "Book products")
	@PostMapping(path = "/user/booking/add/{id}")
	public void BookProduct(int id, String[] code, int[] quantity)
			throws ProductNotFoundException, OutofStockException, UserNotFoundException {
		bookingserviceint.add(id, code, quantity);
	}

	// Get the Booked Products of a User
	@ApiOperation(value = "See My bookings")
	@GetMapping(path = "/user/bookings/find/{id}")
	public List<BookingProduct> retrieveallBooking(@PathVariable("id") int a)
			throws NoOrderBookedException, UserNotFoundException {
		return bookingserviceint.getUserBookingByID(a);
	}

}
