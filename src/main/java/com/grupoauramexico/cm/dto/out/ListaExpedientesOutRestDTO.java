/**
 * 
 */
package com.grupoauramexico.cm.dto.out;

import java.util.List;

import com.grupoauramexico.cm.dto.ExpedienteRestDTO;
import com.grupoauramexico.cm.dto.base.BaseOutRestDTO;

import io.micronaut.core.annotation.Introspected;

/**
 * DTO para la busqueda de expedientes
 * @author vaguirre
 *
 */
@Introspected
public class ListaExpedientesOutRestDTO extends BaseOutRestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5464269954625625306L;
	/**
	 * Lista de expedientes
	 */
	private List<ExpedienteRestDTO> data;
	/**
	 * 
	 */
	public ListaExpedientesOutRestDTO() {
	}
	
	

	/**
	 * @return the data
	 */
	public List<ExpedienteRestDTO> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<ExpedienteRestDTO> data) {
		this.data = data;
	}

}
