/**
 * 
 */
package com.grupoauramexico.cm.dto.in;

import java.io.Serializable;

import com.grupoauramexico.cm.dto.ExpedienteLeanRestDTO;
import com.grupoauramexico.cm.dto.archivo.ArchivoInputRestDTO;

import io.micronaut.core.annotation.Introspected;

/**
 * @author vaguire
 *
 */
@Introspected
public class UploadArchivoInRestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8390650784482883135L;
	private ArchivoInputRestDTO archivo = null;
	private ExpedienteLeanRestDTO expediente = null;

	/**
	 * 
	 */
	public UploadArchivoInRestDTO() {
	}

	/**
	 * @return the archivo
	 */
	public ArchivoInputRestDTO getArchivo() {
		return archivo;
	}

	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(ArchivoInputRestDTO archivo) {
		this.archivo = archivo;
	}

	/**
	 * @return the expediente
	 */
	public ExpedienteLeanRestDTO getExpediente() {
		return expediente;
	}

	/**
	 * @param expediente the expediente to set
	 */
	public void setExpediente(ExpedienteLeanRestDTO expediente) {
		this.expediente = expediente;
	}

}
