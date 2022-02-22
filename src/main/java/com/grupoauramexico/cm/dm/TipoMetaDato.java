/**
 * 
 */
package com.grupoauramexico.cm.dm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Catalogo de metadatos
 * 
 * @author Vladimir Aguirre
 *
 */
@Entity
@Table(name = "CFG_TIPO_METADATO")
public class TipoMetaDato implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5439742161846562263L;
	@Id
	@SequenceGenerator(name = "CFG_TIPO_METADATO_ID_GENERATOR", sequenceName = "CFG_TIPO_METADATO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CFG_TIPO_METADATO_ID_GENERATOR")
	@Column(name = "ID_TIPO_METADATO")
	private Long id;

	/**
	 * Nombre de negocio del metadato
	 */
	@Column(name = "NOMBRE", length = 80, nullable = false)
	private String nombre;
	@Column(name = "DESCRIPCION", length = 120, nullable = false)
	private String descripcion;

	



	/**
	 * 
	 */
	public TipoMetaDato() {
		super();
	}

	public TipoMetaDato(Long id) {
		super();
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	

}
