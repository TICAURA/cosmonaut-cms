/**
 * 
 */
package com.grupoauramexico.cm.dto;

import java.util.Date;
import java.util.List;

/**
 * Representa un expediente
 * 
 * @author vladimir.aguirre
 *
 */
public class ExpedienteRestDTO extends CreacionExpedienteRestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2264362149763053228L;

	private List<TipoDocumentoRestDTO> docsCargados = null;
	private Date fechaRegistro;
	/**
	 * Se actualiza cuando recibe un documento
	 */
	private Date fechaModificacion;

	private CatalogoRestDTO estado = null;


	public ExpedienteRestDTO(Long id) {
		super(id);
	}

	/**
	 * @return the docsCargados
	 */
	public List<TipoDocumentoRestDTO> getDocsCargados() {
		return docsCargados;
	}

	/**
	 * @param docsCargados the docsCargados to set
	 */
	public void setDocsCargados(List<TipoDocumentoRestDTO> docsCargados) {
		this.docsCargados = docsCargados;
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
	 * @return the estado
	 */
	public CatalogoRestDTO getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(CatalogoRestDTO estado) {
		this.estado = estado;
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
