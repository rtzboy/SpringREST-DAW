package com.daw.production.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.daw.production.exception.ResourceNotFoundException;
import com.daw.production.model.Pedido;
import com.daw.production.repository.OrderRepository;
import com.daw.production.repository.PedidoRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PedidoController {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping("/orders/{orderId}/pedidos")
	public ResponseEntity<List<Pedido>> getAllPedidosByOrderId(@PathVariable(value = "orderId") Long orderId) {
		if (!orderRepository.existsById(orderId)) {
			throw new ResourceNotFoundException("Not found Order with id = " + orderId);
		}

		List<Pedido> pedidos = pedidoRepository.findByOrderId(orderId);
		return new ResponseEntity<>(pedidos, HttpStatus.OK);
	}

	@GetMapping("/pedidos/{id}")
	public ResponseEntity<Pedido> getPedidosByOrderId(@PathVariable(value = "id") Long id) {
		Pedido pedido = pedidoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found pedido with id = " + id));

		return new ResponseEntity<>(pedido, HttpStatus.OK);
	}

	@PostMapping("/orders/{orderId}/pedidos")
	public ResponseEntity<Pedido> createPedido(@PathVariable(value = "orderId") Long orderId,
			@RequestBody Pedido pedidoRequest) {
		Pedido pedido = orderRepository.findById(orderId).map(order -> {
			pedidoRequest.setOrder(order);
			return pedidoRepository.save(pedidoRequest);
		}).orElseThrow(() -> new ResourceNotFoundException("Not found Order with id = " + orderId));
		return new ResponseEntity<>(pedido, HttpStatus.CREATED);
	}

	@PutMapping("/pedidos/{id}")
	public ResponseEntity<Pedido> updatePedido(@PathVariable("id") long id, @RequestBody Pedido pedidoRequest) {
		Pedido pedido = pedidoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("OrderId " + id + "not found"));

		pedido.setNombre(pedidoRequest.getNombre());
		pedido.setCantidad(pedidoRequest.getCantidad());
		pedido.setUnidad(pedidoRequest.getUnidad());
		pedido.setStsordveri(pedidoRequest.isStsordveri());
		pedido.setStspedveri(pedidoRequest.isStspedveri());

		return new ResponseEntity<>(pedidoRepository.save(pedido), HttpStatus.OK);
	}

	@DeleteMapping("/pedidos/{id}")
	public ResponseEntity<HttpStatus> deletePedido(@PathVariable("id") long id) {
		pedidoRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/orders/{orderId}/pedidos")
	public ResponseEntity<List<Pedido>> deleteAllPedidosOfOrder(@PathVariable(value = "orderId") Long orderId) {
		if (!pedidoRepository.existsById(orderId)) {
			throw new ResourceNotFoundException("Not found Product with id = " + orderId);
		}
		pedidoRepository.deleteByOrderId(orderId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
