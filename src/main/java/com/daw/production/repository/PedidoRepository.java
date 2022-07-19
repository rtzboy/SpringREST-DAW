package com.daw.production.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.production.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	List<Pedido> findByOrderId(Long postId);

	@Transactional
	void deleteByOrderId(long orderId);
}
