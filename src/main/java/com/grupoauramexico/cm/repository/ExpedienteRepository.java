/**
 * 
 */
package com.grupoauramexico.cm.repository;

import java.util.List;

import com.grupoauramexico.cm.dm.Expediente;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

/**
 * @author vaguire
 *
 */
@Repository
public interface ExpedienteRepository extends JpaRepository<Expediente, Long> {
	@Query(value = "SELECT new Expediente(b.id) FROM Expediente b WHERE b.id= :id", nativeQuery = false)
	Expediente consultarOnlyId(Long id);
	
	@Query(value = "SELECT b FROM Expediente b WHERE b.clave= :clave", nativeQuery = false)
	List<Expediente> consultar(String clave);

}
