package com.daw.production.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.daw.production.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByNombreContaining(String nombre);
	
}
