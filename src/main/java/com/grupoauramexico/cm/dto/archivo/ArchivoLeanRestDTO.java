/**
 * 
 */
package com.grupoauramexico.cm.dto.archivo;

import com.grupoauramexico.cm.dto.base.BaseRestDTO;

/**
 * @author vaguire
 *
 */
public class ArchivoLeanRestDTO extends BaseRestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1737189830416585101L;
	private String nombre;

	/**
	 * 
	 */
	public ArchivoLeanRestDTO() {
	}

	/**
	 * @param id
	 */
	public ArchivoLeanRestDTO(Long id) {
		super(id);
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
