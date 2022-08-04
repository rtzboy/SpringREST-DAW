package com.daw.production.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.daw.production.model.Referencia;

public interface ReferenciaRepository extends JpaRepository<Referencia, Long> {
	
	List<Referencia> findByReferContaining(String refer);
	
	List<Referencia> findBySend(boolean send);
	
	List<Referencia> findByCtrlqual(boolean ctrlqual);
	
	List<Referencia> findByMixed(boolean mixed);
	
	List<Referencia> findByHomog(boolean homog);
	
	List<Referencia> findByCalor(boolean calor);
	
	List<Referencia> findByPasteu(boolean pasteu);
	
	List<Referencia> findByRefrig(boolean refrig);
	
	List<Referencia> findByReposo(boolean reposo);
	
	List<Referencia> findByCongel(boolean congel);
	
	List<Referencia> findByEmbalaje(boolean embalaje);
	
	List<Referencia> findByFreeze(boolean freeze);
	
	List<Referencia> findByFinished(boolean finished);
	

}
