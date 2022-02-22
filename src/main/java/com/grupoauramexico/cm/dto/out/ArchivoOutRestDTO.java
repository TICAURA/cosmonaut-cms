/**
 * 
 */
package com.grupoauramexico.cm.dto.out;

import com.grupoauramexico.cm.commons.enums.ResponseCode;
import com.grupoauramexico.cm.dto.archivo.ArchivoLeanRestDTO;
import com.grupoauramexico.cm.dto.base.BaseOutRestDTO;

/**
 * @author vaguire
 *
 */
public class ArchivoOutRestDTO extends BaseOutRestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2072833184742872584L;
	private ArchivoLeanRestDTO data;
	/**
	 * 
	 */
	public ArchivoOutRestDTO() {
	}

	/**
	 * @param codigo
	 * @param tokenOpe
	 */
	public ArchivoOutRestDTO(ResponseCode codigo, String tokenOpe) {
		super(codigo, tokenOpe);
	}

	/**
	 * @return the data
	 */
	public ArchivoLeanRestDTO getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(ArchivoLeanRestDTO data) {
		this.data = data;
	}

}
