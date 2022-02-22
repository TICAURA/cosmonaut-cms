/**
 * 
 */
package com.grupoauramexico.cm.dto;

import io.micronaut.core.annotation.Introspected;

/**
 * @author vaguirre
 *
 */
@Introspected
public class ExpedienteLeanRestDTO extends CatalogoRestDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8517035941323913483L;

	/**
	 * @param id
	 */
	public ExpedienteLeanRestDTO(Long id) {
		super(id);
	}

}
