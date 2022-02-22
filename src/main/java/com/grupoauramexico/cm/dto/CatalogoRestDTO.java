package com.grupoauramexico.cm.dto;

import com.grupoauramexico.cm.dto.base.BaseRestDTO;

/**
 * Representa el item de un catalogo.
 */
public class CatalogoRestDTO extends BaseRestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2089029340643687276L;
	private String nombre;
	/**
	 * Representa la clave de negocio. Pudo o no haber sido generada por CM, si es
	 * enviada por el cliente al momento de crear el registro se valida que esa
	 * única en la tabla y se guarda el valor recibido.
	 */
	protected String clave;
	/**
	 * 
	 * @param id
	 */
	public CatalogoRestDTO(Long id) {
		super(id);
	}

	public CatalogoRestDTO() {
		super();
	}

	/**
	 * @param clave
	 */
	public CatalogoRestDTO(String clave) {
		super(clave);
	}

	/**
	 * 
	 * @param idP
	 * @param claveCat
	 */
	public CatalogoRestDTO(Long idP, String claveCat) {
		super(idP, claveCat);
	}

	/**
	 * 
	 * @param idP
	 * @param claveCat
	 * @param nombreCat
	 */
	public CatalogoRestDTO(Long idP, String claveCat, String nombreCat) {
		super(idP, claveCat);
		this.nombre = nombreCat;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}
}
