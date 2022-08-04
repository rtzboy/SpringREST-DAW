package com.daw.production.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.production.exception.ResourceNotFoundException;
import com.daw.production.model.Insumo;
import com.daw.production.repository.InsumoRepository;
import com.daw.production.repository.ReferenciaRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class InsumoController {

	@Autowired
	private ReferenciaRepository referenciaRepository;

	@Autowired
	private InsumoRepository insumoRepository;
	
	@GetMapping("/referencias/{referId}/insumos")
	public ResponseEntity<List<Insumo>> getAllInsumosByReferenceId(@PathVariable(value = "referId") Long referId) {
		if (!referenciaRepository.existsById(referId)) {
			throw new ResourceNotFoundException("Not found Reference with id = " + referId);
		}

		List<Insumo> insumos = insumoRepository.findByReferenciaId(referId);
		return new ResponseEntity<>(insumos, HttpStatus.OK);
	}

	@PostMapping("/referencias/{referId}/insumos")
	public ResponseEntity<Insumo> createInsumo(@PathVariable(value = "referId") Long referId,
			@RequestBody Insumo insumoRequest) {
		Insumo insumo = referenciaRepository.findById(referId).map(referencia -> {
			insumoRequest.setReferencia(referencia);
			return insumoRepository.save(insumoRequest);
		}).orElseThrow(() -> new ResourceNotFoundException("Not found Product with id = " + referId));
		return new ResponseEntity<>(insumo, HttpStatus.CREATED);

	}
	
	
}
