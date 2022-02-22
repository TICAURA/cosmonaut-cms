/**
 * 
 */
package com.grupoauramexico.cm.dto.out;

import com.grupoauramexico.cm.dto.CatTipoDocumentoRestDTO;
import com.grupoauramexico.cm.dto.base.BaseOutRestDTO;

import io.micronaut.core.annotation.Introspected;

/**
 * DTO de respuesta para la creacion de tipos de documento
 * 
 * @author vaguirre
 *
 */
@Introspected
public class TipoDocumentosOutRestDTO extends BaseOutRestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5464269954625625306L;
	/**
	 * DTO de salida
	 */
	private CatTipoDocumentoRestDTO data;

	/**
	 * 
	 */
	public TipoDocumentosOutRestDTO() {
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
