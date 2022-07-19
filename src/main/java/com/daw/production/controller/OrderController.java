package com.daw.production.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.production.exception.ResourceNotFoundException;
import com.daw.production.model.Order;
import com.daw.production.repository.OrderRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	OrderRepository orderRepository;

	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getAllOrders(@RequestParam(required = false) String numorden) {
		List<Order> orders = new ArrayList<Order>();

		if (numorden == null)
			orderRepository.findAll().forEach(orders::add);
		if (orders.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@GetMapping("/orders/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable("id") long id) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Order with id= " + id));

		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@PostMapping("/orders")
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		Order _order = orderRepository.save(new Order(order.getNumorden(), order.getFecha(), order.isEstado()));
		return new ResponseEntity<>(_order, HttpStatus.CREATED);
	}

	@PutMapping("/orders/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable("id") long id, @RequestBody Order order) {
		Order _order = orderRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Order with id = " + id));

		_order.setNumorden(order.getNumorden());
		_order.setFecha(order.getFecha());
		_order.setEstado(order.isEstado());

		return new ResponseEntity<>(orderRepository.save(_order), HttpStatus.OK);
	}

	@DeleteMapping("/orders/{id}")
	public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("id") long id) {
		orderRepository.deleteById(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/orders")
	public ResponseEntity<HttpStatus> deleteAllOrders() {
		orderRepository.deleteAll();

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/orders/estado")
	public ResponseEntity<List<Order>> findByEstado() {
		List<Order> orders = orderRepository.findByEstado(true);

		if (orders.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

}
