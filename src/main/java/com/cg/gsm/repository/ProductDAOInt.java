package com.cg.gsm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.gsm.entities.Product;

public interface ProductDAOInt extends JpaRepository<Product, String> {

}
