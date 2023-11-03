package com.cg.gsm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.gsm.entities.Product;
import com.cg.gsm.exception.ProductNotFoundException;
import com.cg.gsm.repository.ProductDAOInt;

@Service
public class ProductServiceIntImp implements ProductServiceInt {

	@Autowired
	private ProductDAOInt productdaoint;

	// To add a product
	@Override
	public Product add(Product bean) {
		return productdaoint.save(bean);
	}

	// To delete a Product
	@Override
	public void deleteByCode(String a) throws ProductNotFoundException {
		try {
			productdaoint.deleteById(a);
		} catch (Exception e) {
			throw new ProductNotFoundException("Product with code= " + a + " Not Found");
		}
		
	}

	// To get a Product with its code
	@Override
	public Product findByCode(String code) throws ProductNotFoundException {
		Optional<Product> u = productdaoint.findById(code);
		if (u.isPresent())
			return u.get();
		throw new ProductNotFoundException("Product with code= " + code + " Not Found");
	}

	// To get all Products
	@Override
	public List<Product> findAll() {
		return productdaoint.findAll();
	}

	// To update a Product
	@Override
	public Product updateProduct(Product product) throws ProductNotFoundException {
		Optional<Product> p = productdaoint.findById(product.getCode());
		if (p.isPresent()) {
			productdaoint.save(product);
			return product;
		}
		throw new ProductNotFoundException("Product with id= " + product.getCode() + " Not Found");
	}

}
