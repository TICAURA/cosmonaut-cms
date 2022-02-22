/**
 * 
 */
package com.grupoauramexico.cm.dto.out;

import java.util.List;

import com.grupoauramexico.cm.dto.TipoMultimediaRestDTO;
import com.grupoauramexico.cm.dto.base.BaseOutRestDTO;

import io.micronaut.core.annotation.Introspected;

/**
 * DTO de respuesta para la consulta de tipos de multimedias
 * @author vaguirre
 *
 */
@Introspected
public class ListaMultimediasOutRestDTO extends BaseOutRestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5464269954625625306L;
	/**
	 * Lista de expedientes
	 */
	private List<TipoMultimediaRestDTO> data;
	/**
	 * 
	 */
	public ListaMultimediasOutRestDTO() {
	}
	
	

	/**
	 * @return the data
	 */
	public List<TipoMultimediaRestDTO> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<TipoMultimediaRestDTO> data) {
		this.data = data;
	}

}
