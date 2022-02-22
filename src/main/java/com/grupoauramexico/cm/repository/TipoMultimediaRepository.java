/**
 * 
 */
package com.grupoauramexico.cm.repository;

import java.util.List;

import com.grupoauramexico.cm.dm.TipoMultimedia;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

/**
 * @author vaguire
 *
 */
@Repository
public interface TipoMultimediaRepository extends JpaRepository<TipoMultimedia, Long> {
	/**
	 * 
	 * @param mimeType
	 * @param extension
	 * @return
	 */
	@Query(value = "SELECT b FROM TipoMultimedia b WHERE  b.mimeType= :mimeType AND b.extension= :extension", nativeQuery = false)
	List<TipoMultimedia> consultar(String mimeType, String extension);
}
