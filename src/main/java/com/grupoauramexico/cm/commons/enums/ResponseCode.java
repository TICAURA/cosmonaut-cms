/**
 * 
 */
package com.grupoauramexico.cm.commons.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vladimir Aguirre Piedragil
 *
 */
public enum ResponseCode {
	/**
	 * 
	 */
	EXITO("WSE000", "Successful");

	private String code;
	private String description;

	private static final Map<String, ResponseCode> map = new HashMap<String, ResponseCode>();

	static {
		final ResponseCode[] values = ResponseCode.values();
		for (int i = 0; i < values.length; i++) {
			map.put(values[i].getCode(), values[i]);
		}

	}

	/**
	 * @param clave
	 * @param descripcion
	 */
	private ResponseCode(String clave, String descripcion) {
		this.code = clave;
		this.description = descripcion;
	}

	public String getCode() {
		return code;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Regresa la clave y la descripcón concatenados.
	 * 
	 * @return
	 */
	public String getCodeDesc() {
		return "[" + code + "] " + description;
	}

	public String concatCodeDesc(String msg) {
		return getCodeDesc() + " : " + msg;
	}

	public static ResponseCode getByCode(String code) {
		return map.get(code);
	}
}
