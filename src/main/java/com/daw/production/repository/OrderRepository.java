package com.daw.production.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.daw.production.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	List<Order> findByEstado(boolean estado);
}
