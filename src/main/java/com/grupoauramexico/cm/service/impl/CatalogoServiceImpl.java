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
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.grupoauramexico.cm.commons.enums.ErrorCodes;
import com.grupoauramexico.cm.commons.exception.CMException;
import com.grupoauramexico.cm.commons.exception.NegocioException;
import com.grupoauramexico.cm.commons.utils.DTOUtils;
import com.grupoauramexico.cm.commons.utils.MsgUtils;
import com.grupoauramexico.cm.dm.TipoDocumento;
import com.grupoauramexico.cm.dm.TipoMultimedia;
import com.grupoauramexico.cm.dto.CatTipoDocumentoRestDTO;
import com.grupoauramexico.cm.dto.TipoMultimediaRestDTO;
import com.grupoauramexico.cm.repository.TipoDocumentoRepository;
import com.grupoauramexico.cm.repository.TipoMultimediaRepository;
import com.grupoauramexico.cm.service.CatalogoService;

/**
 * @author vaguire
 *
 */
@Singleton
@Transactional
public class CatalogoServiceImpl implements CatalogoService {
	private static final Logger LOG = LoggerFactory.getLogger(CatalogoServiceImpl.class);
	private static final int LONGITUD_MAX_TD_ACRONIMO = 15;
	private static final int LONGITUD_MAX_TD_NOMBRE= 80;
	private static final int LONGITUD_MAX_TD_DESC = 120;
	private static final int LONGITUD_MAX_MM = 240;

	@Inject
	private TipoMultimediaRepository mmDao;
	@Inject
	private TipoDocumentoRepository tiposDocsDao;

	@Override
	public List<TipoMultimediaRestDTO> getMultimedias() {
		final long timeIni = System.currentTimeMillis();
		if (LOG.isInfoEnabled()) {
			LOG.info("Iniciar - getMultimedias()");
		}
		final List<TipoMultimediaRestDTO> resp = new ArrayList<TipoMultimediaRestDTO>();
		final List<TipoMultimedia> data = mmDao.findAll();
		for (TipoMultimedia row : data) {
			final TipoMultimediaRestDTO dto = new TipoMultimediaRestDTO(row.getId());
			dto.setMimeType(row.getMimeType());
			dto.setExtension(row.getExtension());
			resp.add(dto);
		}

		if (LOG.isInfoEnabled()) {
			final long timeFin = System.currentTimeMillis();
			LOG.info("Fin - getMultimedias() en " + (timeFin - timeIni) + " ms");
		}
		return resp;
	}

	@Override
	public List<CatTipoDocumentoRestDTO> getTiposDocumentos() {
		final long timeIni = System.currentTimeMillis();
		if (LOG.isInfoEnabled()) {
			LOG.info("Iniciar - getTiposDocumentos()");
		}
		final List<CatTipoDocumentoRestDTO> resp = new ArrayList<CatTipoDocumentoRestDTO>();
		final List<TipoDocumento> data = tiposDocsDao.findAll();
		for (TipoDocumento row : data) {
			final CatTipoDocumentoRestDTO dto = new CatTipoDocumentoRestDTO(row.getId());
			dto.setAcronimo(row.getAcronimo());
			dto.setNombre(row.getNombre());
			dto.setOrden(row.getOrden());
			dto.setDescripcion(row.getDescripcion());
			dto.setActivo(row.getActivo());
			resp.add(dto);
		}

		if (LOG.isInfoEnabled()) {
			final long timeFin = System.currentTimeMillis();
			LOG.info("Fin - getTiposDocumentos() en " + (timeFin - timeIni) + " ms");
		}
		return resp;
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public CatTipoDocumentoRestDTO crear(CatTipoDocumentoRestDTO in) throws CMException {

		final long timeIni = System.currentTimeMillis();
		if (LOG.isInfoEnabled()) {
			LOG.info("Iniciar - crear()");
		}
		if (in == null || StringUtils.isBlank(in.getAcronimo())) {
			LOG.warn("La clave del tipo doc es requerida");
			throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO, "El acr\u00F3imo es requerido");
		} else {
			if (in.getAcronimo().length() > LONGITUD_MAX_TD_ACRONIMO) {
				LOG.warn("Acronimo es muy largo");
				throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO,
						MsgUtils.longitud("acr\u00F3nimo", "data", LONGITUD_MAX_TD_ACRONIMO));
			}
		}
		validarTipoDoc(in);
		TipoDocumento ent = null;
		final List<TipoDocumento> data = tiposDocsDao.consultar(in.getAcronimo());
		
		
		if (CollectionUtils.isNotEmpty(data)) {
			ent = data.get(0);
			if (BooleanUtils.isTrue(ent.getActivo())) {
				LOG.warn("Doc existe");
				throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO,
						"Ya existe un tipo de documento con el acr\u00F3imo proporcionado");
			}
		}
		if (ent == null) {
			ent = new TipoDocumento();
		}

		ent.setAcronimo(in.getAcronimo());
		ent.setActivo(Boolean.TRUE);
		ent.setNombre(in.getNombre());
		ent.setDescripcion(in.getDescripcion());
		ent.setOrden(in.getOrden());
		ent.setAdicional(Boolean.TRUE);
		this.tiposDocsDao.save(ent);
		in.setId(ent.getId());
		in.setActivo(ent.getActivo());
		if (LOG.isInfoEnabled()) {
			final long timeFin = System.currentTimeMillis();
			LOG.info("Fin - crear() en " + (timeFin - timeIni) + " ms");
		}

		return in;
	}

	private void validarTipoDoc(CatTipoDocumentoRestDTO in) throws NegocioException {
		if (!StringUtils.isNoneBlank(in.getNombre(), in.getDescripcion())) {
			throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO,
					"Nombre y decripci\u00F3n son requeridos");
		}
		if (in.getNombre().length() > LONGITUD_MAX_TD_NOMBRE) {
			LOG.warn("Nombre es muy largo");
			throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO,
					MsgUtils.longitud("nombre", "data", LONGITUD_MAX_TD_NOMBRE));
		}
		if (in.getDescripcion().length() > LONGITUD_MAX_TD_DESC) {
			LOG.warn("desc es muy largo");
			throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO,
					MsgUtils.longitud("descripci\u00F3n", "data", LONGITUD_MAX_TD_DESC));
		}
	}

	@Override
	public CatTipoDocumentoRestDTO modificar(CatTipoDocumentoRestDTO in) throws CMException {
		final long timeIni = System.currentTimeMillis();
		if (LOG.isInfoEnabled()) {
			LOG.info("Iniciar - modificar()");
		}
		if (!DTOUtils.isValid(in)) {
			LOG.warn("La id del tipo doc es requerida");
			throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO, "El id es requerido");
		}
		validarTipoDoc(in);

		final Optional<TipoDocumento> oprtd = this.tiposDocsDao.findById(in.getId());
		if (!oprtd.isPresent()) {
			throw new NegocioException(ErrorCodes.TIPO_DOCUMENTO_NO_EXISTE,
					"No fue posible localiar el id en la base de datos");
		}
		final TipoDocumento ent = oprtd.get();
		ent.setAcronimo(in.getAcronimo());
		ent.setActivo(Boolean.TRUE);
		ent.setNombre(in.getNombre());
		ent.setDescripcion(in.getDescripcion());
		ent.setOrden(in.getOrden());
		ent.setAdicional(Boolean.TRUE);
		this.tiposDocsDao.update(ent);
		in.setActivo(ent.getActivo());
		if (LOG.isInfoEnabled()) {
			final long timeFin = System.currentTimeMillis();
			LOG.info("Fin - modificar() en " + (timeFin - timeIni) + " ms");
		}
		return in;
	}

	@Override
	public CatTipoDocumentoRestDTO eliminar(CatTipoDocumentoRestDTO in) throws CMException {
		final long timeIni = System.currentTimeMillis();
		if (LOG.isInfoEnabled()) {
			LOG.info("Iniciar - eliminar()");
		}
		if (!DTOUtils.isValid(in)) {
			LOG.warn("La id del tipo doc es requerida");
			throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO, "El id es requerido");
		}

		final Optional<TipoDocumento> oprtd = this.tiposDocsDao.findById(in.getId());
		if (!oprtd.isPresent()) {
			throw new NegocioException(ErrorCodes.TIPO_DOCUMENTO_NO_EXISTE,
					"No fue posible localiar el id en la base de datos");
		}
		final TipoDocumento ent = oprtd.get();

		final Long count = this.tiposDocsDao.countDocumentosCargados(ent.getId());
		if (count.longValue() > 0) {
			throw new NegocioException(ErrorCodes.NEGOCIO_ESTADO_INCORRECTO,
					"Existen " + count + " documentos cargados en la base de datos. No puede ser eliminado");
		}

		ent.setActivo(Boolean.FALSE);
		this.tiposDocsDao.update(ent);
		in.setActivo(ent.getActivo());
		if (LOG.isInfoEnabled()) {
			final long timeFin = System.currentTimeMillis();
			LOG.info("Fin - eliminar() en " + (timeFin - timeIni) + " ms");
		}
		return in;
	}

	@Override
	public CatTipoDocumentoRestDTO activar(CatTipoDocumentoRestDTO in) throws CMException {
		final long timeIni = System.currentTimeMillis();
		if (LOG.isInfoEnabled()) {
			LOG.info("Iniciar - activar()");
		}
		if (!DTOUtils.isValid(in)) {
			LOG.warn("La id del tipo doc es requerida");
			throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO, "El id es requerido");
		}

		final Optional<TipoDocumento> oprtd = this.tiposDocsDao.findById(in.getId());
		if (!oprtd.isPresent()) {
			throw new NegocioException(ErrorCodes.TIPO_DOCUMENTO_NO_EXISTE,
					"No fue posible localiar el id en la base de datos");
		}
		final TipoDocumento ent = oprtd.get();

		ent.setActivo(Boolean.TRUE);
		this.tiposDocsDao.update(ent);
		in.setActivo(ent.getActivo());
		if (LOG.isInfoEnabled()) {
			final long timeFin = System.currentTimeMillis();
			LOG.info("Fin - activar() en " + (timeFin - timeIni) + " ms");
		}
		return in;
	}

	@Override
	public TipoMultimediaRestDTO crear(TipoMultimediaRestDTO in) throws CMException {
		final long timeIni = System.currentTimeMillis();
		if (LOG.isInfoEnabled()) {
			LOG.info("Iniciar - crear()");
		}
		if (  in == null || StringUtils.isBlank(in.getExtension()) || StringUtils.isBlank(in.getMimeType())) {
			LOG.warn("Faltan datos");
			throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO, "La extensi\u00F3n y el MimeType son requeridos");
		} else {
			if (in.getExtension().length() > LONGITUD_MAX_MM) {
				LOG.warn("EXT es muy largo");
				throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO,
						MsgUtils.longitud("extensi\u00F3n", "data", LONGITUD_MAX_MM));
			}
			if (in.getMimeType().length() > LONGITUD_MAX_MM) {
				LOG.warn("EXT es muy largo");
				throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO,
						MsgUtils.longitud("mimeType", "data", LONGITUD_MAX_MM));
			}
		}
		TipoMultimedia ent = null;
		final List<TipoMultimedia> data = mmDao.consultar(in.getMimeType(), in.getExtension());
		
		
		if (CollectionUtils.isNotEmpty(data)) {
			ent = data.get(0);
				LOG.warn("MM existe");
				throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO,
						"Ya existe un tipo multimedia con los datos proporcionados");
		}
		if (ent == null) {
			ent = new TipoMultimedia();
		}

		ent.setMimeType(in.getMimeType());
		ent.setExtension(in.getExtension());
		this.mmDao.save(ent);
		in.setId(ent.getId());
		if (LOG.isInfoEnabled()) {
			final long timeFin = System.currentTimeMillis();
			LOG.info("Fin - crear() en " + (timeFin - timeIni) + " ms");
		}

		return in;
	}

}
