/**
 * 
 */
package com.grupoauramexico.cm.dto.in;

import java.io.Serializable;

import com.grupoauramexico.cm.dto.CreacionExpedienteRestDTO;

import io.micronaut.core.annotation.Introspected;

/**
 * @author vladimir.aguirre
 *
 */
@Introspected
public class FiltroExpedienteRestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -350212932371656199L;
	
	private CreacionExpedienteRestDTO data = null;

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
