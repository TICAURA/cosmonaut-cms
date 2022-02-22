/**
 * 
 */
package com.grupoauramexico.cm.service;

import java.util.List;

import com.grupoauramexico.cm.commons.exception.CMException;
import com.grupoauramexico.cm.dto.archivo.ArchivoLeanRestDTO;
import com.grupoauramexico.cm.dto.archivo.ArchivoRestDTO;
import com.grupoauramexico.cm.dto.in.UploadArchivoInRestDTO;
import com.grupoauramexico.cm.dto.in.VersionArchivoInRestDTO;

/**
 * @author vaguire
 *
 */
public interface ArchivosService {
	ArchivoRestDTO agregarArchivo(UploadArchivoInRestDTO archDto) throws CMException;

	ArchivoRestDTO consultar(ArchivoLeanRestDTO in) throws CMException;

	ArchivoRestDTO versionar(VersionArchivoInRestDTO archDto) throws CMException;

	ArchivoRestDTO reemplazar(VersionArchivoInRestDTO archDto) throws CMException;

	List<ArchivoRestDTO> consultarVersiones(ArchivoLeanRestDTO in) throws CMException;
	
	/**
	 * Elimina una rhivo de anera lógica
	 * @param in
	 * @return
	 * @throws CMException
	 */
	ArchivoRestDTO elimiar(ArchivoLeanRestDTO in) throws CMException;
}
