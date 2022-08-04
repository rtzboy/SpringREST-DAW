package com.daw.production.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.production.exception.ResourceNotFoundException;
import com.daw.production.model.Referencia;
import com.daw.production.repository.ReferenciaRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ReferenciaController {

	@Autowired
	ReferenciaRepository referenciaRepository;

	@GetMapping("/referencias")
	public ResponseEntity<List<Referencia>> getAllReferences(@RequestParam(required = false) String refer) {
		List<Referencia> references = new ArrayList<Referencia>();

		if (refer == null)
			referenciaRepository.findAll().forEach(references::add);
		else
			referenciaRepository.findByReferContaining(refer).forEach(references::add);

		if (references.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(references, HttpStatus.OK);
	}

	@GetMapping("/referencias/{id}")
	public ResponseEntity<Referencia> getOrderById(@PathVariable("id") long id) {
		Referencia referencia = referenciaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Referencia with id= " + id));

		return new ResponseEntity<>(referencia, HttpStatus.OK);
	}

	@PostMapping("/referencias")
	public ResponseEntity<Referencia> createReferencia(@RequestBody Referencia referencia) {
		Referencia _referencia = referenciaRepository.save(new Referencia(referencia.getRefer(), referencia.getFecha(),
				referencia.isSend(), referencia.isCtrlqual(), referencia.isMixed(), referencia.isHomog(),
				referencia.isCalor(), referencia.isPasteu(), referencia.isRefrig(), referencia.isReposo(),
				referencia.isCongel(), referencia.isEmbalaje(), referencia.isFreeze(), referencia.isFinished()));
		return new ResponseEntity<>(_referencia, HttpStatus.CREATED);
	}

	@PutMapping("/referencias/{id}")
	public ResponseEntity<Referencia> updateReferencia(@PathVariable("id") long id,
			@RequestBody Referencia referencia) {
		Referencia _referencia = referenciaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Referencia with id = " + id));

		_referencia.setRefer(referencia.getRefer());
		_referencia.setFecha(referencia.getFecha());
		_referencia.setSend(referencia.isSend());
		_referencia.setCtrlqual(referencia.isCtrlqual());
		_referencia.setMixed(referencia.isMixed());
		_referencia.setHomog(referencia.isHomog());
		_referencia.setCalor(referencia.isCalor());
		_referencia.setPasteu(referencia.isPasteu());
		_referencia.setRefrig(referencia.isRefrig());
		_referencia.setReposo(referencia.isReposo());
		_referencia.setCongel(referencia.isCongel());
		_referencia.setEmbalaje(referencia.isEmbalaje());
		_referencia.setFreeze(referencia.isFreeze());
		_referencia.setFinished(referencia.isFinished());
		return new ResponseEntity<>(referenciaRepository.save(_referencia), HttpStatus.OK);
	}

	// get "send" estatus true
	@GetMapping("/referencias/send")
	public ResponseEntity<List<Referencia>> findByEstado() {
		List<Referencia> orders = referenciaRepository.findBySend(true);
		if (orders.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	// get "ctrlqual" estatus true
	@GetMapping("/referencias/ctrlqual")
	public ResponseEntity<List<Referencia>> findByCtrlqual() {
		List<Referencia> orders = referenciaRepository.findByCtrlqual(true);
		if (orders.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	// get "mixed" estatus true
	@GetMapping("/referencias/mixed")
	public ResponseEntity<List<Referencia>> findByMixedphase() {
		List<Referencia> orders = referenciaRepository.findByMixed(true);
		if (orders.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	// get "homog" estatus true
	@GetMapping("/referencias/homo")
	public ResponseEntity<List<Referencia>> findByHomophase() {
		List<Referencia> orders = referenciaRepository.findByHomog(true);
		if (orders.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	// get "calor" estatus true
	@GetMapping("/referencias/calor")
	public ResponseEntity<List<Referencia>> findByCalorphase() {
		List<Referencia> orders = referenciaRepository.findByCalor(true);
		if (orders.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	// get "pasteu" estatus true
	@GetMapping("/referencias/pasteu")
	public ResponseEntity<List<Referencia>> findByPasteuphase() {
		List<Referencia> orders = referenciaRepository.findByPasteu(true);
		if (orders.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	// get "refrig" estatus true
	@GetMapping("/referencias/refrig")
	public ResponseEntity<List<Referencia>> findByRefrigphase() {
		List<Referencia> orders = referenciaRepository.findByRefrig(true);
		if (orders.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	// get "reposo" estatus true
	@GetMapping("/referencias/reposo")
	public ResponseEntity<List<Referencia>> findByReposophase() {
		List<Referencia> orders = referenciaRepository.findByReposo(true);
		if (orders.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	// get "congel" estatus true
	@GetMapping("/referencias/congel")
	public ResponseEntity<List<Referencia>> findByCongelphase() {
		List<Referencia> orders = referenciaRepository.findByCongel(true);
		if (orders.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	// get "embalaje" estatus true
	@GetMapping("/referencias/embalaje")
	public ResponseEntity<List<Referencia>> findByEmbalajephase() {
		List<Referencia> orders = referenciaRepository.findByEmbalaje(true);
		if (orders.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	// get "freeze" estatus true
	@GetMapping("/referencias/freeze")
	public ResponseEntity<List<Referencia>> findByFreezephase() {
		List<Referencia> orders = referenciaRepository.findByFreeze(true);
		if (orders.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	// get "finished" estatus true
	@GetMapping("/referencias/finished")
	public ResponseEntity<List<Referencia>> findByFinishedphase() {
		List<Referencia> orders = referenciaRepository.findByFinished(true);
		if (orders.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

}
