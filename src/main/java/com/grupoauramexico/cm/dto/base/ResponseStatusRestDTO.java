package com.grupoauramexico.cm.dto.base;

import java.io.Serializable;
import java.util.Date;

import com.grupoauramexico.cm.commons.enums.ErrorCodes;
import com.grupoauramexico.cm.commons.enums.ResponseCode;

import io.micronaut.core.annotation.Introspected;

/**
 * DTO que representa el respuesta de la invocación de un servicio
 * @author Vladimir Aguirre
 *
 */
@Introspected
public class ResponseStatusRestDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 801316002777123768L;

	/**
	 * Código de respuesta
	 */
	private String code;

	private String desc;
	private String tokenOperation;
	private Date timeOperation;

	public ResponseStatusRestDTO(ResponseCode code) {
		this.code = code.getCode();
		this.desc = code.getDescription();
	}

	public ResponseStatusRestDTO(ErrorCodes code) {
		this.code = code.getCode();
		this.desc = code.getDescription();
	}

	public String getTokenOperation() {
		return tokenOperation;
	}

	public void setTokenOperation(String tokenOperation) {
		this.tokenOperation = tokenOperation;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the timeOperation
	 */
	public Date getTimeOperation() {
		return timeOperation;
	}

	/**
	 * @param timeOperation the timeOperation to set
	 */
	public void setTimeOperation(Date timeOperation) {
		this.timeOperation = timeOperation;
	}

}
