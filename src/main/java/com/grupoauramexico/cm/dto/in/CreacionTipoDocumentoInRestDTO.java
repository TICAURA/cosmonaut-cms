/**
 * 
 */
package com.grupoauramexico.cm.dto.in;

import java.io.Serializable;

import com.grupoauramexico.cm.dto.CatTipoDocumentoRestDTO;

import io.micronaut.core.annotation.Introspected;

/**
 * @author vaguirre
 *
 */
@Introspected
public class CreacionTipoDocumentoInRestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8390650784482883135L;
	private CatTipoDocumentoRestDTO data = null;

	/**
	 * 
	 */
	public CreacionTipoDocumentoInRestDTO() {
	}

	/**
	 * @return the data
	 */
	public CatTipoDocumentoRestDTO getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(CatTipoDocumentoRestDTO data) {
		this.data = data;
	}

}
