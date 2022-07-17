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
import com.daw.production.model.Product;
import com.daw.production.repository.ProductRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String nombre) {
		List<Product> products = new ArrayList<Product>();

		if (nombre == null)
			productRepository.findAll().forEach(products::add);
		else
			productRepository.findByNombreContaining(nombre).forEach(products::add);

		if (products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Product with id= " + id));

		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@PostMapping("/products")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		Product _product = productRepository.save(new Product(product.getNombre(), product.getCategoria(),
				product.getUrlimage(), product.getFecharegistro()));
		return new ResponseEntity<>(_product, HttpStatus.CREATED);
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
		Product _product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found product with id = " + id));

		_product.setNombre(product.getNombre());
		_product.setCategoria(product.getCategoria());
		_product.setUrlimage(product.getUrlimage());
		_product.setFecharegistro(product.getFecharegistro());

		return new ResponseEntity<>(productRepository.save(_product), HttpStatus.OK);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
		productRepository.deleteById(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/products")
	public ResponseEntity<HttpStatus> deleteAllProducts() {
		productRepository.deleteAll();

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
