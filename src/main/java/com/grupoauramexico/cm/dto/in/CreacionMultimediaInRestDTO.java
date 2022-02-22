/**
 * 
 */
package com.grupoauramexico.cm.dto.in;

import java.io.Serializable;

import com.grupoauramexico.cm.dto.TipoMultimediaRestDTO;

import io.micronaut.core.annotation.Introspected;

/**
 * @author vaguirre
 *
 */
@Introspected
public class CreacionMultimediaInRestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8390650784482883135L;
	private TipoMultimediaRestDTO data = null;

	/**
	 * 
	 */
	public CreacionMultimediaInRestDTO() {
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
