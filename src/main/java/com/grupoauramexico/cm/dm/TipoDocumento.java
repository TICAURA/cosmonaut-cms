/**
 * 
 */
package com.grupoauramexico.cm.dm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <ul>
 * <li>Estado de cuenta</li>
 * <li>Contrato</li>
 * <li>INE</li>
 * <li><i>etc.</i></li>
 * </ul>
 * 
 * @author Vladimir Aguirre
 *
 */
@Entity
@Table(name = "TIPO_DOCUMENTO")
public class TipoDocumento implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2209755454025190804L;
	@Id
	@SequenceGenerator(name = "TIPO_DOCUMENTO_ID_GENERATOR", sequenceName = "SEQ_TIPO_DOCUMENTO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPO_DOCUMENTO_ID_GENERATOR")
	@Column(name = "ID_TIPO_DOCUMENTO")
	private Long id;
	@Column(name = "NOMBRE", length = 80, nullable = false)
	private String nombre;
	@Column(name = "ACRONIMO", length = 15, nullable = false)
	private String acronimo;
	@Column(name = "DESCRIPCION", length = 120, nullable = false)
	private String descripcion;
	@Column(name = "IND_ACTIVO", nullable = false)
	private Boolean activo = Boolean.TRUE;

	@Column(name = "ORDEN ")
	private Integer orden;

	@Column(name = "IND_REPETIBLE", nullable = false)
	private Boolean repetible = Boolean.FALSE;
	@Column(name = "IND_ADICIONAL", nullable = false)
	private Boolean adicional;
	@Column(name = "IND_ESCANEO", nullable = false)
	private Boolean escaneo = Boolean.FALSE;

	/**
	 * Si el valor es <code>false</code> significa que el sistema lo genera o que
	 * existe una pantalla exclusiva para la carga de este documento
	 */
	@Column(name = "IND_CARGABLE", nullable = true)
	private Boolean cargable = Boolean.FALSE;

	/**
	 * 
	 */
	public TipoDocumento() {
		super();
	}

	public TipoDocumento(Long id) {
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
	 * @return the repetible
	 */
	public Boolean getRepetible() {
		return repetible;
	}

	/**
	 * @param repetible the repetible to set
	 */
	public void setRepetible(Boolean repetible) {
		this.repetible = repetible;
	}

	/**
	 * @return the adicional
	 */
	public Boolean getAdicional() {
		return adicional;
	}

	/**
	 * @param adicional the adicional to set
	 */
	public void setAdicional(Boolean adicional) {
		this.adicional = adicional;
	}

	/**
	 * @return the escaneo
	 */
	public Boolean getEscaneo() {
		return escaneo;
	}

	/**
	 * @param escaneo the escaneo to set
	 */
	public void setEscaneo(Boolean escaneo) {
		this.escaneo = escaneo;
	}

	/**
	 * @return the cargable
	 */
	public Boolean getCargable() {
		return cargable;
	}

	/**
	 * @param cargable the cargable to set
	 */
	public void setCargable(Boolean cargable) {
		this.cargable = cargable;
	}

}
