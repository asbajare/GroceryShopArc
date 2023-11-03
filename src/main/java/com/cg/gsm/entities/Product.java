package com.cg.gsm.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {

	@Id
	@NotBlank(message = "Product code is mandatory")
	private String code;

	@NotBlank(message = "Product name is mandatory")
	private String name;

	@Positive(message = "Product price should be greater than 0")
	private double price;

	@NotBlank(message = "Product description is mandatory")
	@Size(max = 255)
	private String description;

	@Positive(message = "Product quantity in stock should be greater than 0")
	private int quantityInStock;

	@NotBlank(message = "Product category is mandatory")
	private String category;

	@JsonIgnore
	@OneToMany(mappedBy = "booking", fetch = FetchType.EAGER)
	private List<BookingProduct> bookingproducts;

	public Product() {
		super();
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public String getCategory() {
		return category;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<BookingProduct> getBookingproducts() {
		return bookingproducts;
	}

	public void setBookingproducts(List<BookingProduct> bookingproducts) {
		this.bookingproducts = bookingproducts;
	}
}
