/**
 * All Rights Reserved.
 * Archivo creado 19 mar 2020
 */
package com.grupoauramexico.cm.commons.utils;

import com.grupoauramexico.cm.dto.base.BaseRestDTO;

/**
 * Clase para realizar operaciones basicas con <code>BaseCoreDTO</code>
 * 
 * @author Vladimir Aguirre Piedragil
 *
 */
public class DTOUtils {
	/**
	 * Método para validar que el dto o y su <code>id</code> sean nulos.
	 * 
	 * @param dto
	 * @return
	 */
	public static boolean isNull(BaseRestDTO dto) {
		return dto == null || dto.getId() == null;
	}

	/**
	 * El dto y su <code>id</code> son diferente de null y mayor a cero
	 * (<code>0</code>)
	 * 
	 * @param dto
	 * @return
	 */
	public static boolean isValid(BaseRestDTO dto) {
		return isNotNull(dto) && dto.getId() > 0;
	}

	/**
	 * Método para validar que el dto ni su <code>id</code> sean nulos.
	 * 
	 * @param dto
	 * @return
	 */
	public static boolean isNotNull(BaseRestDTO dto) {
		return dto != null && dto.getId() != null;
	}

}
