/**
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
 * Clase que contiene los datos para un estado que puede tener un Episodio
 * <code>(Evento)</code>.
 * 
 * @see EstadosProceso
 * 
 * @author Vladimir Aguirre
 * 
 */
@Entity
@Table(name = "ESTADO_PROCESO")
public class EstadoProceso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2924070285174637886L;

	/**
	 * Identificador unico en la base de datos para el estado de una tabla de
	 * negocio.
	 */
	@Id
	@SequenceGenerator(name = "ESTADO_PRO_ID_GENERATOR", sequenceName = "SEQ_ESTADO_PROCESO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESTADO_PRO_ID_GENERATOR")
	@Column(name = "ID_ESTADO_PROCESO")
	private Long id;

	/** Estado de negocio para la entidad. Descripción para el operador */
	@Column(name = "NEGOCIO", length = 40, nullable = false)
	private String negocio;
	/** Estado de negocio para la entidad, descripción para el cliente. */
	@Column(name = "CLIENTE", length = 40, nullable = false)
	private String cliente;
	@Column(name = "DESCRIPCION", length = 200, nullable = false)
	private String descripcion;
	/** Especifica si el estado de la entidad es activo o inactivo. */
	@Column(name = "IND_ACTIVO")
	private Boolean activo;
	@Column(name = "PRIORIDAD ")
	private Integer prioridad;
	@Column(name = "IND_MODIFICA_HOSPITAL")
	private Boolean modificaHospital;
	
	@Column(name = "IND_PERMITE_ESCANEAR", nullable = false)
	private Boolean indPermiteEscanear;

	/** Constructor por defecto. */
	public EstadoProceso() {
		super();
	}

	/**
	 * 
	 * @param id
	 */
	public EstadoProceso(Long id) {
		super();
		this.id = id;
	}

	/**
	 * Crea un nuevo EstadoEntidad con un identificador, estado de negocio y estado
	 * de HL7.
	 * 
	 * @param id      Identificador �nico en la base de datos para el estado de una
	 *                entidad.
	 * @param negocio Estado de negocio para la entidad.
	 * @see <a href=
	 *      "http://vico.org/HL7_V3_CD1_2013/Edition2013/infrastructure/vocabulary/EntityStatus.html">
	 *      C�digos para los estados de Entidades HL7</a>
	 * @param activo Especifica si el estado la entidad es activo o inactivo.
	 */
	public EstadoProceso(Long id, String negocio, Boolean activo) {
		this.id = id;
		this.negocio = negocio;
		this.activo = activo;
	}

	/**
	 * Obtiene el identificador �nico en la base de datos para el estado de una
	 * entidad.
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Coloca el identificador �nico en la base de datos para el estado de una
	 * entidad.
	 * 
	 * @param id Identificador a colocar.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtiene el estado de negocio para la entidad.
	 * 
	 * @return negocio
	 */
	public String getNegocio() {
		return negocio;
	}

	/**
	 * Coloca el estado de negocio para la entidad.
	 * 
	 * @param negocio Estado de negocio a colocar.
	 */
	public void setNegocio(String negocio) {
		this.negocio = negocio;
	}

	/**
	 * Obtiene el estado para el estado de una entidad: activo o inactivo.
	 * 
	 * @return activo
	 */
	public Boolean getActivo() {
		return activo;
	}

	/**
	 * Coloca el estado para el estado de una entidad: activo o inactivo.
	 * 
	 * @param activo Estado a colocar.
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	/**
	 * @return the cliente
	 */
	public String getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the prioridad
	 */
	public Integer getPrioridad() {
		return prioridad;
	}

	/**
	 * @param prioridad the prioridad to set
	 */
	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	/**
	 * @return the modificaHospital
	 */
	public Boolean getModificaHospital() {
		return modificaHospital;
	}

	/**
	 * @param modificaHospital the modificaHospital to set
	 */
	public void setModificaHospital(Boolean modificaHospital) {
		this.modificaHospital = modificaHospital;
	}

	/**
	 * @return the indPermiteEscanear
	 */
	public Boolean getIndPermiteEscanear() {
		return indPermiteEscanear;
	}

	/**
	 * @param indPermiteEscanear the indPermiteEscanear to set
	 */
	public void setIndPermiteEscanear(Boolean indPermiteEscanear) {
		this.indPermiteEscanear = indPermiteEscanear;
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
