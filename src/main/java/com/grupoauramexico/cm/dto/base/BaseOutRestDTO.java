/**
 * 
 */
package com.grupoauramexico.cm.dto.base;

import java.io.Serializable;

import com.grupoauramexico.cm.commons.enums.ResponseCode;

import io.micronaut.core.annotation.Introspected;

/**
 * Todo los <code>DTO's</code> de <b>salida</b> de cada servicio REST deben
 * extender de este objeto
 * 
 * @author Vladimir Aguirre
 *
 */
@Introspected
public class BaseOutRestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4255406295299690441L;
	protected ResponseStatusRestDTO responseStatus = new ResponseStatusRestDTO(ResponseCode.EXITO);

	public BaseOutRestDTO() {
		super();
	}

	public BaseOutRestDTO(ResponseCode codigo, String tokenOpe) {
		super();
		responseStatus = new ResponseStatusRestDTO(codigo);
		responseStatus.setTokenOperation(tokenOpe);
	}

	public ResponseStatusRestDTO getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatusRestDTO responseStatus) {
		this.responseStatus = responseStatus;
	}

}
