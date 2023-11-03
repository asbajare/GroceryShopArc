package com.cg.gsm.service;

import java.util.List;

import com.cg.gsm.entities.Product;
import com.cg.gsm.exception.ProductNotFoundException;

public interface ProductServiceInt {

	public Product add(Product bean);

	public void deleteByCode(String a) throws ProductNotFoundException;

	public Product findByCode(String code) throws ProductNotFoundException;

	public List<Product> findAll();

	public Product updateProduct(Product product) throws ProductNotFoundException;

}
