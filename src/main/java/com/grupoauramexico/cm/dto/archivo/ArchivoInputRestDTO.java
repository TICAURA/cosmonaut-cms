/**
 * 
 */
package com.grupoauramexico.cm.dto.archivo;

import com.grupoauramexico.cm.dto.TipoDocumentoRestDTO;
import com.grupoauramexico.cm.dto.TipoMultimediaRestDTO;

/**
 * Para guardar archivos
 * @author vaguirre
 *
 */
public class ArchivoInputRestDTO extends ArchivoLeanRestDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7103742387951971563L;
	private String contenido;

	private TipoDocumentoRestDTO tipoDocto;
	private TipoMultimediaRestDTO tipoMM;
	private String comentario;

	/**
	 * 
	 */
	public ArchivoInputRestDTO() {
	}

	/**
	 * @param id
	 */
	public ArchivoInputRestDTO(Long id) {
		super(id);
	}

	/**
	 * @return the tipoDocto
	 */
	public TipoDocumentoRestDTO getTipoDocto() {
		return tipoDocto;
	}

	/**
	 * @param tipoDocto the tipoDocto to set
	 */
	public void setTipoDocto(TipoDocumentoRestDTO tipoDocto) {
		this.tipoDocto = tipoDocto;
	}

	/**
	 * @return the tipoMM
	 */
	public TipoMultimediaRestDTO getTipoMM() {
		return tipoMM;
	}

	/**
	 * @param tipoMM the tipoMM to set
	 */
	public void setTipoMM(TipoMultimediaRestDTO tipoMM) {
		this.tipoMM = tipoMM;
	}

	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * @return the contenido
	 */
	public String getContenido() {
		return contenido;
	}

	/**
	 * @param contenido the contenido to set
	 */
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

}
