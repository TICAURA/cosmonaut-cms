/**
 * 
 */
package com.grupoauramexico.cm.service;

import com.grupoauramexico.cm.commons.exception.CMException;
import com.grupoauramexico.cm.dto.ExpedienteLeanRestDTO;
import com.grupoauramexico.cm.dto.ExpedienteRestDTO;
import com.grupoauramexico.cm.dto.in.CreacionExpedienteInRestDTO;

/**
 * @author vaguire
 *
 */
public interface ExpedienteService {
	ExpedienteRestDTO consultar(ExpedienteLeanRestDTO in) throws CMException;

	ExpedienteRestDTO crear(CreacionExpedienteInRestDTO in) throws CMException;
}
