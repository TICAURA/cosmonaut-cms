/**
 * 
 */
package com.grupoauramexico.cm.dto.archivo;

import java.util.Date;

import io.micronaut.core.annotation.Introspected;

/**
 * Representa un archivo con su contenido en base 64.
 * 
 * @author vladimir.aguirre
 *
 */
@Introspected
public class ArchivoRestDTO extends ArchivoInputRestDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7193484050121558900L;
	private boolean obsoleto = false;
	private Date fechaRegistro;

	private Integer numeroVersion;
	private Long idOriginal;
	/**
	 * Base 64
	 */

	private Float sizeKb;
	private String hashMd5;

	public ArchivoRestDTO() {
		super();
	}

	public ArchivoRestDTO(Long id) {
		super(id);
	}

	/**
	 * @return the obsoleto
	 */
	public boolean isObsoleto() {
		return obsoleto;
	}

	/**
	 * @param obsoleto the obsoleto to set
	 */
	public void setObsoleto(boolean obsoleto) {
		this.obsoleto = obsoleto;
	}

	/**
	 * @return the numeroVersion
	 */
	public Integer getNumeroVersion() {
		return numeroVersion;
	}

	/**
	 * @param numeroVersion the numeroVersion to set
	 */
	public void setNumeroVersion(Integer numeroVersion) {
		this.numeroVersion = numeroVersion;
	}

	/**
	 * @return the idOriginal
	 */
	public Long getIdOriginal() {
		return idOriginal;
	}

	/**
	 * @param idOriginal the idOriginal to set
	 */
	public void setIdOriginal(Long idOriginal) {
		this.idOriginal = idOriginal;
	}

	/**
	 * @return the sizeKb
	 */
	public Float getSizeKb() {
		return sizeKb;
	}

	/**
	 * @param sizeKb the sizeKb to set
	 */
	public void setSizeKb(Float sizeKb) {
		this.sizeKb = sizeKb;
	}

	/**
	 * @return the hashMd5
	 */
	public String getHashMd5() {
		return hashMd5;
	}

	/**
	 * @param hashMd5 the hashMd5 to set
	 */
	public void setHashMd5(String hashMd5) {
		this.hashMd5 = hashMd5;
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
