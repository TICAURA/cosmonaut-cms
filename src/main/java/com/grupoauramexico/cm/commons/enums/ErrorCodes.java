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
public enum ErrorCodes {
	/* ===== INFRAESTRCUTURA ===== */
	INFRA_CONFIGURACION("INF400", "Problemas de configuraci\u00F3n"),
	INFRA_EXTERNO_NO_DISPONIBLE("INF402", "El servicio externo no est\u00E1 disponible"),
	/* ===== GENERICOS ===== */
	/**
	 * Retornar este mensaje cuando suceda un excepción del tipo
	 * <code>Exception</code>, es decir, algo que no controlamos ya no sabes que
	 * está pasando
	 */
	GENERICO_INTERNO("GEN500", "Error gen\u00E9rico interno"),
	/* ===== SEGURIDAD ===== */
	SEGURIDAD_USUARIO_NO_EXISTE("SEG505", "El usuario no existe"), SEGURIDAD_PROHIBIDO("SEG401", "Error de permisos"),
	SEGURIDAD_APLICACION_NO_AUTORIZADA("SEG402", "Aplicaci\u00F3n no autorizada"),
	/* ===== NEGOCIO ===== */
	/**
	 * Retornar cuando haya inconsistencias de negocio, más una descripción de la
	 * inconsistencia.
	 */
	NEGOCIO_ESTADO_INCORRECTO("NEG100", "Estado incorrecto de negocio"),
	/**
	 * Retornar cuando el parámetro sea incorrecto
	 */
	NEGOCIO_ARGUMENTO_INCORRECTO("NEG101", "Argumento de negocio incorrecto"),
	/**
	 * Retornar cuando falte un parámetro
	 */
	NEGOCIO_ARGUMENTO_REQUERIDO("NEG105", "Argumento de negocio requerido"),
	/**
	 * Expediente en BD
	 */
	NEGOCIO_EXPEDIENTE_NO_EXISTE("NEG201", "Expediente no localizado"),
	NEGOCIO_ARCHIVO_NO_EXISTE("NEG202", "Archivo no localizado"),
	TIPO_DOCUMENTO_NO_EXISTE("NEG203", "Tipo documento no localizado");

	private String code;
	private String description;

	private static final Map<String, ErrorCodes> map = new HashMap<String, ErrorCodes>();

	static {
		final ErrorCodes[] values = ErrorCodes.values();
		for (int i = 0; i < values.length; i++) {
			map.put(values[i].getCode(), values[i]);
		}

	}

	/**
	 * @param clave
	 * @param descripcion
	 */
	private ErrorCodes(String clave, String descripcion) {
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

	public static ErrorCodes getByCode(String code) {
		return map.get(code);
	}
}
