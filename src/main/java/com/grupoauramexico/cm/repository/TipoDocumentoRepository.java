package com.grupoauramexico.cm.repository;

import java.util.List;

import com.grupoauramexico.cm.dm.TipoDocumento;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {
	/**
	 * 
	 * @param acronimo
	 * @return
	 */
	@Query(value = "SELECT b FROM TipoDocumento b WHERE b.acronimo= :acronimo", nativeQuery = false)
	List<TipoDocumento> consultar(String acronimo);
	/**
	 * 
	 * @param acronimo
	 * @return
	 */
	@Query(value = "SELECT b FROM TipoDocumento b WHERE b.activo = true AND b.acronimo= :acronimo", nativeQuery = false)
	List<TipoDocumento> consultarActivo(String acronimo);
	@Query(value = "SELECT count(b.id) FROM Archivo b WHERE b.obsoleto=false AND b.tipoDocumento.id= :idDoc", nativeQuery = false)
	Long countDocumentosCargados(Long idDoc);
	
}
