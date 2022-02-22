/**
 * 
 */
package com.grupoauramexico.cm.dto;

import io.micronaut.core.annotation.Introspected;

/**
 * DTO con los datos para crear un expediente
 * 
 * @author vaguire
 *
 */
@Introspected
public class CreacionExpedienteRestDTO extends ExpedienteLeanRestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -450066364605787873L;

	/**
	 * @param id
	 */
	public CreacionExpedienteRestDTO(Long id) {
		super(id);
	}

}
