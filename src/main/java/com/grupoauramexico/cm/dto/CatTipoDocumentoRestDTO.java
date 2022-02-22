/**
 * 
 */
package com.grupoauramexico.cm.dto;

import com.grupoauramexico.cm.dto.base.BaseRestDTO;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.annotation.RequestAttribute;

/**
 * @author vaguirre
 *
 */
@Introspected
public class CatTipoDocumentoRestDTO extends BaseRestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4559508665516302520L;
	private String nombre;
	private String acronimo;
	private String descripcion;
	private Integer orden;
	private Boolean activo;

	/**
	 * 
	 */
	public CatTipoDocumentoRestDTO() {
		super();
	}

	/**
	 * @param id
	 * @param clave
	 */
	public CatTipoDocumentoRestDTO(Long id, String clave) {
		super(id, clave);
	}

	/**
	 * @param id
	 */
	public CatTipoDocumentoRestDTO(Long id) {
		super(id);
	}

	/**
	 * @param clave
	 */
	public CatTipoDocumentoRestDTO(String clave) {
		super(clave);
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

	/**
	 * @return the acronimo
	 */
	public String getAcronimo() {
		return acronimo;
	}

	/**
	 * @param acronimo the acronimo to set
	 */
	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the orden
	 */
	public Integer getOrden() {
		return orden;
	}

	/**
	 * @param orden the orden to set
	 */
	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	/**
	 * @return the activo
	 */
	public Boolean getActivo() {
		return activo;
	}

	/**
	 * @param activo the activo to set
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

}
