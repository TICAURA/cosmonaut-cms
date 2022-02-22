/**
 * 
 */
package com.grupoauramexico.cm.dto.out;

import com.grupoauramexico.cm.dto.ExpedienteRestDTO;
import com.grupoauramexico.cm.dto.base.BaseOutRestDTO;

/**
 *  DTO para la consulta de un expediente
 * @author vaguire
 *
 */
public class ExpedienteOutRestDTO extends BaseOutRestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5464269954625625306L;
	/**
	 * Datos del expediente
	 */
	private ExpedienteRestDTO data;

	/**
	 * 
	 */
	public ExpedienteOutRestDTO() {
	}

	/**
	 * @return the data
	 */
	public ExpedienteRestDTO getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(ExpedienteRestDTO data) {
		this.data = data;
	}

}
