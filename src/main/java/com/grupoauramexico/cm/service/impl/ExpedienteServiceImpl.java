/**
 * 
 */
package com.grupoauramexico.cm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.grupoauramexico.cm.commons.enums.ErrorCodes;
import com.grupoauramexico.cm.commons.exception.CMException;
import com.grupoauramexico.cm.commons.exception.NegocioException;
import com.grupoauramexico.cm.commons.utils.DTOUtils;
import com.grupoauramexico.cm.commons.utils.MsgUtils;
import com.grupoauramexico.cm.commons.utils.UtilDates;
import com.grupoauramexico.cm.dm.Archivo;
import com.grupoauramexico.cm.dm.Expediente;
import com.grupoauramexico.cm.dto.ExpedienteLeanRestDTO;
import com.grupoauramexico.cm.dto.ExpedienteRestDTO;
import com.grupoauramexico.cm.dto.TipoDocumentoRestDTO;
import com.grupoauramexico.cm.dto.archivo.ArchivoLeanReadRestDTO;
import com.grupoauramexico.cm.dto.in.CreacionExpedienteInRestDTO;
import com.grupoauramexico.cm.repository.ArchivoRepository;
import com.grupoauramexico.cm.repository.ExpedienteRepository;
import com.grupoauramexico.cm.service.ExpedienteService;

/**
 * @author vaguirre
 *
 */
@Singleton
@Transactional
public class ExpedienteServiceImpl implements ExpedienteService {
	private static final Logger LOG = LoggerFactory.getLogger(ExpedienteServiceImpl.class);
	
	private static final int LONGITUD_MAX_CLAVE_EXP = 50;

	@Inject
	private ExpedienteRepository expDao;
	@Inject
	private ArchivoRepository archDao;

	@Override
	public ExpedienteRestDTO consultar(ExpedienteLeanRestDTO in) throws CMException {
		final long timeIni = System.currentTimeMillis();
		if (LOG.isInfoEnabled()) {
			LOG.info("Iniciar - consultar()");
		}

		Expediente exp = null;
		if (DTOUtils.isValid(in)) {
			final Optional<Expediente> option = expDao.findById(in.getId());
			exp = option.isPresent() ? option.get() : null;
		} else {
			final List<Expediente> data = expDao.consultar(in.getClave());
			if (CollectionUtils.isNotEmpty(data)) {
				exp = data.get(0);
			}
		}
		if (exp == null) {
			throw new CMException(ErrorCodes.NEGOCIO_EXPEDIENTE_NO_EXISTE);
		}
		final ExpedienteRestDTO dto = new ExpedienteRestDTO(exp.getId());
		dto.setClave(exp.getClave());
		dto.setFechaRegistro(exp.getFechaRegistro());
		dto.setFechaRegistro(exp.getFechaModificacion());
		final List<Archivo> data = archDao.consultarByExp(exp.getId());
		final List<TipoDocumentoRestDTO> docs = new ArrayList<TipoDocumentoRestDTO> ();
		if (CollectionUtils.isNotEmpty(data)) {
			for (final Archivo row : data) {
				final TipoDocumentoRestDTO doc = new TipoDocumentoRestDTO();
				final ArchivoLeanReadRestDTO arch = new ArchivoLeanReadRestDTO(row.getId());
				arch.setFechaRegistro(row.getFechaRegistro());
				arch.setNombre(row.getNombre());
				doc.setAcronimo(row.getTipoDocumento().getAcronimo());
				doc.setNombre(row.getTipoDocumento().getNombre());
				doc.setDescripcion(row.getTipoDocumento().getDescripcion());
				doc.setOrden(row.getTipoDocumento().getOrden());
				doc.setArchivo(arch);
				docs.add(doc);
			}
		}
		dto.setDocsCargados(docs);
		if (LOG.isInfoEnabled()) {
			final long timeFin = System.currentTimeMillis();
			LOG.info("Fin - consultar() en " + (timeFin - timeIni) + " ms");
		}

		return dto;
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public ExpedienteRestDTO crear(CreacionExpedienteInRestDTO in) throws CMException {
		final long timeIni = System.currentTimeMillis();
		if (LOG.isInfoEnabled()) {
			LOG.info("Iniciar - crear()");
		}
		if (in == null || in.getData() == null || StringUtils.isBlank(in.getData().getClave())) {
			LOG.warn("La clave del expediente es requerida");
			throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO, "La clave del expediente es requerida");
		} else {
			if (in.getData().getClave().length() > LONGITUD_MAX_CLAVE_EXP) {
				LOG.warn("La clave del expediente es muy larga");
				throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO,
						MsgUtils.longitud("clave", "data", LONGITUD_MAX_CLAVE_EXP));
			}
		}

		final List<Expediente> data = expDao.consultar(in.getData().getClave());
		if (CollectionUtils.isNotEmpty(data)) {
			LOG.warn("Expediente existe");
			throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO, "Ya existe un expediente con la clave proporcionada");
		}
		
		final Expediente ent = new Expediente();
		
		



		ent.setClave(in.getData().getClave());
		ent.setFechaRegistro(UtilDates.getCurrentDate());
		this.expDao.save(ent);
		final ExpedienteRestDTO resp = new ExpedienteRestDTO(ent.getId());
		resp.setClave(ent.getClave());
		if (LOG.isInfoEnabled()) {
			final long timeFin = System.currentTimeMillis();
			LOG.info("Fin - crear() en " + (timeFin - timeIni) + " ms");
		}

		return resp;

	}

}
