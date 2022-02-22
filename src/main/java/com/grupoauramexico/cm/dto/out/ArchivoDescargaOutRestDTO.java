package com.grupoauramexico.cm.dto.out;

import com.grupoauramexico.cm.commons.enums.ResponseCode;
import com.grupoauramexico.cm.dto.archivo.ArchivoRestDTO;
import com.grupoauramexico.cm.dto.base.BaseOutRestDTO;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class ArchivoDescargaOutRestDTO extends BaseOutRestDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5961832128362630434L;
	private ArchivoRestDTO data;

	public ArchivoDescargaOutRestDTO() {
	}

	public ArchivoDescargaOutRestDTO(ResponseCode codigo, String tokenOpe) {
		super(codigo, tokenOpe);
	}

	/**
	 * @return the data
	 */
	public ArchivoRestDTO getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(ArchivoRestDTO data) {
		this.data = data;
	}

}
