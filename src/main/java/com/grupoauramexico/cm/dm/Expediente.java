/**
 * 
 */
package com.grupoauramexico.cm.dm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Expediente individual
 * 
 * @author vladimir.aguirre
 *
 */
@Entity
@Table(name = "EXPEDIENTE")
public class Expediente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4020458024652514586L;

	@Id
	@SequenceGenerator(name = "EXPEDIENTE_ID_GENERATOR", sequenceName = "SEQ_EXPEDIENTE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXPEDIENTE_ID_GENERATOR")
	@Column(name = "ID_EXPEDIENTE")
	private Long id;


	/** Clave. Codigo. */
	@Column(name = "CLAVE", length = 50, nullable = false, unique = true)
	private String clave;
	


	/**
	 * Punto en el tiempo en que se regitra.
	 */
	@Column(name = "FECHA_REGISTRO", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro;
	
	/** Especifica si el estado de la entidad es activo o inactivo. */
	@Column(name = "IND_ACTIVO")
	private Boolean indActivo;
	
	

	/**
	 * Archivos del expediente
	 */
	@OneToMany(mappedBy = "expediente", fetch = FetchType.LAZY)
	private List<Archivo> archivos = new ArrayList<Archivo>();



	/**
	 * Punto en el tiempo en que se modifica.
	 * 
	 * @since 31/07/2019
	 *
	 */
	@Column(name = "FECHA_MODIFICACION", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaModificacion;



	/**
	 * 
	 */
	public Expediente() {
		super();
	}



	/**
	 * @param id
	 */
	public Expediente(Long id) {
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
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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



	/**
	 * @return the indActivo
	 */
	public Boolean getIndActivo() {
		return indActivo;
	}



	/**
	 * @param indActivo the indActivo to set
	 */
	public void setIndActivo(Boolean indActivo) {
		this.indActivo = indActivo;
	}



	/**
	 * @return the archivos
	 */
	public List<Archivo> getArchivos() {
		return archivos;
	}



	/**
	 * @param archivos the archivos to set
	 */
	public void setArchivos(List<Archivo> archivos) {
		this.archivos = archivos;
	}



	/**
	 * @return the fechaModificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}



	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}



	

}
