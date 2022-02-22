package com.grupoauramexico.cm.dto.archivo;

import java.util.Date;

import io.micronaut.core.annotation.Introspected;

/**
 * Para consultas ligeras
 * @author vaguire
 *
 */
@Introspected
public class ArchivoLeanReadRestDTO extends ArchivoLeanRestDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4110193403752888715L;
	private Date fechaRegistro;

	public ArchivoLeanReadRestDTO() {
	}

	/**
	 * @param id
	 */
	public ArchivoLeanReadRestDTO(Long id) {
		super(id);
	}

	/**
	 * @return the fechaRegistro
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * @param fechaRegistro the fechaRegistro to set
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

}
