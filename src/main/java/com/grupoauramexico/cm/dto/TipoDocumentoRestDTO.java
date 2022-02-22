/**
 * 
 */
package com.grupoauramexico.cm.dto;

import com.grupoauramexico.cm.dto.archivo.ArchivoLeanReadRestDTO;
import com.grupoauramexico.cm.dto.base.BaseRestDTO;

/**
 * @author vladimir.aguirre
 *
 */
public class TipoDocumentoRestDTO extends CatTipoDocumentoRestDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6660273430653062948L;

	private ArchivoLeanReadRestDTO archivo;

	/**
	 * 
	 */
	public TipoDocumentoRestDTO() {
		super();
	}

	/**
	 * @param id
	 * @param clave
	 */
	public TipoDocumentoRestDTO(Long id, String clave) {
		super(id, clave);
	}

	/**
	 * @param id
	 */
	public TipoDocumentoRestDTO(Long id) {
		super(id);
	}

	/**
	 * @param clave
	 */
	public TipoDocumentoRestDTO(String clave) {
		super(clave);
	}

	/**
	 * @return the archivo
	 */
	public ArchivoLeanReadRestDTO getArchivo() {
		return archivo;
	}

	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(ArchivoLeanReadRestDTO archivo) {
		this.archivo = archivo;
	}



}
