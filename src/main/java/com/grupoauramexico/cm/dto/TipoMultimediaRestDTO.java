/**
 * 
 */
package com.grupoauramexico.cm.dto;

/**
 * @author <a href="mailto:vlad.pax@gmail.com">Vladimir Aguirre Piedragil</a>
 *
 */
public class TipoMultimediaRestDTO extends CatalogoRestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8399970183471425099L;
	public String extension;
	private String mimeType;

	public TipoMultimediaRestDTO() {
		super();
	}

	public TipoMultimediaRestDTO(Long id) {
		super(id);
	}

	/**
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * @param extension the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * @return the mimeType
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * @param mimeType the mimeType to set
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
