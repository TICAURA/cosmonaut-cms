/**
 * 
 */
package com.grupoauramexico.cm.dto.base;

import com.grupoauramexico.cm.commons.enums.ErrorCodes;
import com.grupoauramexico.cm.commons.exception.CMException;

/**
 * @author vaguirre
 *
 */
public class ErrorRestDTO extends BaseOutRestDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6542719574951711170L;
	private String mensaje;
	private String causa;

	public ErrorRestDTO(CMException vf, boolean sendMensaje, String tokenOpe) {
		super();
		if (vf != null) {
			if (vf.getCodigo() != null) {
				responseStatus = new ResponseStatusRestDTO(vf.getCodigo());
				this.responseStatus.setCode(vf.getCodigo().getCode());
				if (sendMensaje) {
					this.responseStatus.setDesc(vf.getMessage());
				}else {
					this.responseStatus.setDesc(vf.getCodigo().getDescription());
				}
				this.responseStatus.setTokenOperation(tokenOpe);
			} else {
				super.responseStatus = new ResponseStatusRestDTO(ErrorCodes.GENERICO_INTERNO);
				this.responseStatus.setTokenOperation(tokenOpe);
			}
			if (vf.getCause() != null) {
				if (vf.getCause().getMessage() != null) {
					causa = vf.getCause().getMessage();
				} else {
					causa = vf.getCause().getClass().getSimpleName();
				}
			}
			if (sendMensaje) {
				if (this.responseStatus.getDesc() != null) {
					this.responseStatus.setDesc(this.responseStatus.getDesc());
				}
			}
		}
	}

	public ErrorRestDTO(String desc, String tokenOpe) {
		super();
		responseStatus = new ResponseStatusRestDTO(ErrorCodes.GENERICO_INTERNO);
		this.responseStatus.setDesc(desc);
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the causa
	 */
	public String getCausa() {
		return causa;
	}

	/**
	 * @param causa
	 *            the causa to set
	 */
	public void setCausa(String causa) {
		this.causa = causa;
	}
}
