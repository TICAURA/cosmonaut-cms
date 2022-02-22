/**
 * 
 */
package com.grupoauramexico.cm.dto.in;

import java.io.Serializable;

import com.grupoauramexico.cm.dto.CreacionExpedienteRestDTO;

import io.micronaut.core.annotation.Introspected;

/**
 * @author vaguirre
 *
 */
@Introspected
public class CreacionExpedienteInRestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8390650784482883135L;
	private CreacionExpedienteRestDTO data = null;

	/**
	 * 
	 */
	public CreacionExpedienteInRestDTO() {
	}

	/**
	 * @return the data
	 */
	public CreacionExpedienteRestDTO getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(CreacionExpedienteRestDTO data) {
		this.data = data;
	}

}
