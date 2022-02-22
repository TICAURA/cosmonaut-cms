/**
 * 
 */
package com.grupoauramexico.cm.dto.out;

import com.grupoauramexico.cm.dto.TipoMultimediaRestDTO;
import com.grupoauramexico.cm.dto.base.BaseOutRestDTO;

import io.micronaut.core.annotation.Introspected;

/**
 * DTO de respuesta para la creacion de tipos de multimedias
 * 
 * @author vaguirre
 *
 */
@Introspected
public class MultimediaOutRestDTO extends BaseOutRestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5464269954625625306L;
	/**
	 * Dato de salida
	 */
	private TipoMultimediaRestDTO data;

	/**
	 * 
	 */
	public MultimediaOutRestDTO() {
	}

	/**
	 * @return the data
	 */
	public TipoMultimediaRestDTO getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(TipoMultimediaRestDTO data) {
		this.data = data;
	}

}
