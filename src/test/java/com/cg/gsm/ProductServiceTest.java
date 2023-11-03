package com.cg.gsm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.cg.gsm.entities.Product;
import com.cg.gsm.exception.ProductNotFoundException;
import com.cg.gsm.repository.ProductDAOInt;
import com.cg.gsm.service.ProductServiceIntImp;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@Mock
	private ProductDAOInt productdaoint;// dummy dao layer

	@InjectMocks
	private ProductServiceIntImp productserviceintimp;// service layer

	@Test
	public void testAddProduct() {
		Product p = new Product();
		p.setCategory("Dairy");
		p.setCode("DA001");
		p.setDescription("Fresh Cow Milk");
		p.setName("Amul Milk");
		p.setPrice(50);
		p.setQuantityInStock(100);

		when(productdaoint.save(p)).thenReturn(p);

		Product realp = productserviceintimp.add(p);

		assertEquals(p, realp);

	}

	@Test
	public void testFindByPKProduct() throws ProductNotFoundException {

		Product p = new Product();
		p.setCategory("Dairy");
		p.setCode("DA001");
		p.setDescription("Fresh Cow Milk");
		p.setName("Amul Milk");
		p.setPrice(50);
		p.setQuantityInStock(100);

		when(productdaoint.findById(p.getCode())).thenReturn(Optional.of(p));
		Product realp = productserviceintimp.findByCode("DA001");
		assertEquals("DA001", realp.getCode());

	}

	@Test
	public void testFindAll() {

		Product p1 = new Product();
		p1.setCategory("Dairy");
		p1.setCode("DA001");
		p1.setDescription("Fresh Cow Milk");
		p1.setName("Amul Milk");
		p1.setPrice(50);
		p1.setQuantityInStock(100);

		Product p2 = new Product();
		p2.setCategory("Bakery");
		p2.setCode("BA001");
		p2.setDescription("Fresh Bread");
		p2.setName("Golden Bread");
		p2.setPrice(40);
		p2.setQuantityInStock(100);

		Product p3 = new Product();
		p3.setCategory("Grains");
		p3.setCode("GA001");
		p3.setDescription("Quality Wheat");
		p3.setName("Wheat");
		p3.setPrice(40);
		p3.setQuantityInStock(100);

		List<Product> listofproduct = new ArrayList<Product>();
		listofproduct.add(p1);
		listofproduct.add(p2);
		listofproduct.add(p3);

		when(productdaoint.findAll()).thenReturn(listofproduct);
		List<Product> list = productserviceintimp.findAll();
		assertEquals(listofproduct, list);
	}

}
