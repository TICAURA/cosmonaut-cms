/**
 * 
 */
package com.grupoauramexico.cm.dto.in;

import java.io.Serializable;

import com.grupoauramexico.cm.dto.archivo.ArchivoInputRestDTO;
import com.grupoauramexico.cm.dto.archivo.ArchivoLeanRestDTO;

import io.micronaut.core.annotation.Introspected;

/**
 * @author vaguirre
 *
 */
@Introspected
public class VersionArchivoInRestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8390650784482883135L;
	private ArchivoInputRestDTO archivo = null;
	private ArchivoLeanRestDTO original;

	/**
	 * 
	 */
	public VersionArchivoInRestDTO() {
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
	 * @return the original
	 */
	public ArchivoLeanRestDTO getOriginal() {
		return original;
	}

	/**
	 * @param original the original to set
	 */
	public void setOriginal(ArchivoLeanRestDTO original) {
		this.original = original;
	}

}
