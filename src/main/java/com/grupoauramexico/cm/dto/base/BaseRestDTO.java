package com.grupoauramexico.cm.dto.base;

import java.io.Serializable;

/**
 * DTO base del que deben extender todos los DTO's.
 */
public abstract class BaseRestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4708007351470433746L;
	/**
	 * Identificador único del objeto. Definido siempre por el CM
	 */
	protected Long id;


	/**
	 * Constructor vacío.
	 */
	public BaseRestDTO() {
		super();
	}

	/**
	 * @param clave
	 */
	public BaseRestDTO(String clave) {
		super();
	}

	/**
	 * @param id
	 * @param clave
	 */
	public BaseRestDTO(Long id, String clave) {
		super();
		this.id = id;
	}

	/**
	 * Constructor usando el <code>id</code>.
	 * 
	 * @param id
	 */
	public BaseRestDTO(Long id) {
		super();
		this.id = id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}


}
