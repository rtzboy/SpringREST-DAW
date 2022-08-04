package com.daw.production.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.production.model.Insumo;

public interface InsumoRepository extends JpaRepository<Insumo, Long> {

	List<Insumo> findByReferenciaId(Long refId);

	//	@Transactional
	// void deletedByReferenciaId(Long referId);

}
