/**
 * 
 */
package com.grupoauramexico.cm.dto.out;

import java.util.List;

import com.grupoauramexico.cm.dto.CatTipoDocumentoRestDTO;
import com.grupoauramexico.cm.dto.base.BaseOutRestDTO;

import io.micronaut.core.annotation.Introspected;

/**
 * DTO de respuesta para la consulta de tipos de documentos
 * 
 * @author vaguirre
 *
 */
@Introspected
public class ListaTiposDocumentosOutRestDTO extends BaseOutRestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5464269954625625306L;
	/**
	 * Lista de expedientes
	 */
	private List<CatTipoDocumentoRestDTO> data;

	/**
	 * 
	 */
	public ListaTiposDocumentosOutRestDTO() {
	}

	/**
	 * @return the data
	 */
	public List<CatTipoDocumentoRestDTO> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<CatTipoDocumentoRestDTO> data) {
		this.data = data;
	}

}
