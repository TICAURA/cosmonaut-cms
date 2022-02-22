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
 * @author Vladimir Aguirre
 * @see Multimedias
 */
@Entity
@Table(name = "TIPO_MULTIMEDIA")
public class TipoMultimedia implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2209755454025190804L;
	@Id
	@SequenceGenerator(name = "TMULTIMEDIA_ID_GENERATOR", sequenceName = "SEQ_ID_TIPO_MULTIMEDIA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TMULTIMEDIA_ID_GENERATOR")
	@Column(name = "ID_TIPO_MULTIMEDIA")
	private Long id;
	@Column(name = "MIME_TYPE")
	private String mimeType;
	@Column(name = "EXTENSION")
	private String extension;

	/**
	 * 
	 */
	public TipoMultimedia() {
		super();
	}

	public TipoMultimedia(Long id) {
		super();
		this.id = id;
	}

	/**
	 * @return the mimeType
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * @param mimeType
	 *            the mimeType to set
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * @param extension
	 *            the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
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

}
