package com.grupoauramexico.cm.repository;

import java.util.List;

import com.grupoauramexico.cm.dm.Archivo;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface ArchivoRepository extends JpaRepository<Archivo, Long> {
	@Query(value = "SELECT new Archivo(b.id, b.nombre, b.tipo, b.fechaRegistro, b.fechaModificacion,b.tipoDocumento, b.numeroVersion, b.comentario, b.obsoleto,b.hashMd5, b.sizeKb) FROM Archivo b WHERE b.expediente.id= :idExp and b.indActivo = true", nativeQuery = false)
	List<Archivo> consultarByExp(Long idExp);
	
	@Query(value = "SELECT new Archivo(b.id, b.nombre, b.tipo, b.fechaRegistro, b.fechaModificacion,b.tipoDocumento, b.numeroVersion, b.comentario, b.obsoleto,b.hashMd5, b.sizeKb) FROM Archivo b WHERE b.id= :idARch", nativeQuery = false)
	Archivo consultarById(Long idARch);
	
	@Query(value = "SELECT new Archivo(b.id, b.fechaRegistro, b.original.id) FROM Archivo b WHERE b.id= :idActual", nativeQuery = false)
	List<Archivo> consultarOrignal(Long idActual);

}
