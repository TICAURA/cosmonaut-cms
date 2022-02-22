/**
 * 
 */
package com.grupoauramexico.cm.service;

import java.util.List;

import com.grupoauramexico.cm.commons.exception.CMException;
import com.grupoauramexico.cm.dto.CatTipoDocumentoRestDTO;
import com.grupoauramexico.cm.dto.TipoMultimediaRestDTO;

/**
 * @author vaguire
 *
 */
public interface CatalogoService {

	/**
	 * Consulta catalogo de multimedias
	 * @return
	 */
	List<TipoMultimediaRestDTO> getMultimedias();

	List<CatTipoDocumentoRestDTO> getTiposDocumentos();

	CatTipoDocumentoRestDTO crear(CatTipoDocumentoRestDTO in) throws CMException;

	CatTipoDocumentoRestDTO modificar(CatTipoDocumentoRestDTO in) throws CMException;

	CatTipoDocumentoRestDTO eliminar(CatTipoDocumentoRestDTO in) throws CMException;

	CatTipoDocumentoRestDTO activar(CatTipoDocumentoRestDTO in) throws CMException;

	/**
	 * Rregistra un tipo multimedia
	 * @param data
	 * @return
	 * @throws CMException
	 */
	TipoMultimediaRestDTO crear(TipoMultimediaRestDTO data) throws CMException;

}
