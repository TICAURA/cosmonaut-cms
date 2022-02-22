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

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
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
import com.grupoauramexico.cm.commons.utils.UtilDates;
import com.grupoauramexico.cm.dm.Archivo;
import com.grupoauramexico.cm.dm.Expediente;
import com.grupoauramexico.cm.dm.TipoDocumento;
import com.grupoauramexico.cm.dm.TipoMultimedia;
import com.grupoauramexico.cm.dto.TipoDocumentoRestDTO;
import com.grupoauramexico.cm.dto.TipoMultimediaRestDTO;
import com.grupoauramexico.cm.dto.archivo.ArchivoInputRestDTO;
import com.grupoauramexico.cm.dto.archivo.ArchivoLeanRestDTO;
import com.grupoauramexico.cm.dto.archivo.ArchivoRestDTO;
import com.grupoauramexico.cm.dto.in.UploadArchivoInRestDTO;
import com.grupoauramexico.cm.dto.in.VersionArchivoInRestDTO;
import com.grupoauramexico.cm.repository.ArchivoRepository;
import com.grupoauramexico.cm.repository.ExpedienteRepository;
import com.grupoauramexico.cm.repository.TipoDocumentoRepository;
import com.grupoauramexico.cm.service.ArchivosService;

/**
 * @author vaguire
 *
 */
@Singleton
@Transactional
public class ArchivosServiceImpl implements ArchivosService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ArchivosServiceImpl.class);
	private static final float BYTES_IN_KB = 1024.0f;
	private static final int LONGITUD_MAX_ARCHIVO_NOMBRE= 120;


	@Inject
	private ExpedienteRepository expDao;
	@Inject
	private TipoDocumentoRepository tipoDocDao;

	@Inject
	private ArchivoRepository archivoDao;

	@Override
	public ArchivoRestDTO agregarArchivo(UploadArchivoInRestDTO archDto) throws CMException {
		final ArchivoRestDTO salida = guardarArchivoBD(archDto);
		return salida;
	}

	private ArchivoRestDTO guardarArchivoBD(UploadArchivoInRestDTO archDto) throws NegocioException {
		final long timeIni = System.currentTimeMillis();
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Iniciar - guardarArchivoBD()");
		}

		validarArchivoNuevo(archDto.getArchivo());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Guardando archivo " + archDto.getArchivo().getNombre());
			LOGGER.debug("Tipo documento :: " + archDto.getArchivo().getTipoDocto());

		}
		final Archivo file = new Archivo();

		final List<TipoDocumento> tipoDocs = this.tipoDocDao
				.consultarActivo(archDto.getArchivo().getTipoDocto().getAcronimo());
		if (CollectionUtils.isEmpty(tipoDocs)) {
			throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO,
					"El Tipo Documento proporcionado no existe en la base de datos");
		}
		file.setTipoDocumento(tipoDocs.get(0));

		poblarContenido(archDto.getArchivo(), file);

		file.setFechaRegistro(UtilDates.getCurrentDate());
		file.setNombre(archDto.getArchivo().getNombre());
		file.setComentario(archDto.getArchivo().getComentario());
		file.setNumeroVersion(new Integer(1));
		Expediente exp = null;
		if (DTOUtils.isValid(archDto.getExpediente())) {
			exp = this.expDao.consultarOnlyId(archDto.getExpediente().getId());
		} else {
			final List<Expediente> exps = this.expDao.consultar(archDto.getExpediente().getClave());
			if (CollectionUtils.isNotEmpty(exps)) {
				exp = exps.get(0);
			}
		}
		if (exp == null) {
			throw new NegocioException(ErrorCodes.NEGOCIO_EXPEDIENTE_NO_EXISTE);
		}

		file.setExpediente(exp);
		file.setTipo(new TipoMultimedia(archDto.getArchivo().getTipoMM().getId()));
		archivoDao.save(file);
		archDto.setArchivo(new ArchivoRestDTO());
		archDto.getArchivo().setId(file.getId());

		final ArchivoRestDTO salida = new ArchivoRestDTO();
		salida.setId(file.getId());
		salida.setNumeroVersion(file.getNumeroVersion());
		salida.setSizeKb(file.getSizeKb());
		salida.setHashMd5(file.getHashMd5());

		exp.setFechaModificacion(file.getFechaRegistro());
		this.expDao.update(exp);

		if (LOGGER.isInfoEnabled()) {
			final long timeFin = System.currentTimeMillis();
			LOGGER.info("Fin - guardarArchivoBD() en " + (timeFin - timeIni) + " ms");
		}

		return salida;
	}

	private void poblarContenido(ArchivoInputRestDTO archDto, final Archivo file) {
		final byte[] decodedBytes = Base64.decodeBase64(archDto.getContenido());
		LOGGER.info("decodedBytes.length :: " + decodedBytes.length);
		file.setArchivo(decodedBytes);
		final String md5Hash = DigestUtils.md5Hex(file.getArchivo());
		file.setHashMd5(md5Hash);
		file.setSizeKb(file.getArchivo().length / BYTES_IN_KB);
		file.setObsoleto(false);
		file.setIndActivo(Boolean.TRUE);

	}

	/**
	 * 
	 * @param archDto
	 * @throws NegocioException
	 */
	public void validarArchivoNuevo(ArchivoInputRestDTO archDto) throws NegocioException {
		this.validarArchivo(archDto);
		if (archDto != null && archDto.getTipoDocto() == null
				|| StringUtils.isBlank(archDto.getTipoDocto().getAcronimo())) {
			throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO,
					"El tipo de documento es requerido (acr\u00F3nimo)");
		}

	}

	/**
	 * 
	 * @param archDto
	 * @throws NegocioException
	 */
	public void validarArchivo(ArchivoInputRestDTO archDto) throws NegocioException {
		if (archDto != null && archDto.getContenido() == null) {
			throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO, "El archivo es requerido");
		}
		if (archDto != null && StringUtils.isBlank(archDto.getNombre())) {
			throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO, "El nombre del archivo es requerido");

		}
		if (archDto != null && archDto.getTipoMM() == null) {
			throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO, "El tipo multimedia es requerido");
		}
		if (archDto != null && StringUtils.isBlank(archDto.getNombre())) {
			throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO, "El nombre del archivo es requerido");
		}
		if (archDto.getNombre().length() > LONGITUD_MAX_ARCHIVO_NOMBRE) {
			LOGGER.warn("Nombre archivo muy largo");
			throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO,
					MsgUtils.longitud("nombre", "archivo", LONGITUD_MAX_ARCHIVO_NOMBRE));
		}	
	}

	@Override
	public ArchivoRestDTO consultar(ArchivoLeanRestDTO in) throws CMException {
		final long timeIni = System.currentTimeMillis();
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Iniciar - consultar()");
		}
		final Archivo ent = this.archivoDao.findById(in.getId()).get();
		if (ent == null) {
			LOGGER.warn("Archivo " + in.getId() + " no encontrado");
			return null;
		}
		final ArchivoRestDTO dto = new ArchivoRestDTO(ent.getId());

		final String decodedBytes = Base64.encodeBase64String(ent.getArchivo());
		LOGGER.info("decodedBytes.length :: " + ent.getArchivo().length);
		dto.setContenido(decodedBytes);
		dto.setFechaRegistro(ent.getFechaRegistro());
		dto.setHashMd5(ent.getHashMd5());
		dto.setNombre(ent.getNombre());
		dto.setComentario(ent.getComentario());

		dto.setTipoMM(new TipoMultimediaRestDTO(ent.getTipo().getId()));
		dto.getTipoMM().setMimeType(ent.getTipo().getMimeType());
		dto.getTipoMM().setExtension(ent.getTipo().getExtension());
		if (ent.getTipoDocumento() != null) {
			dto.setTipoDocto(
					new TipoDocumentoRestDTO(ent.getTipoDocumento().getId(), ent.getTipoDocumento().getNombre()));
			dto.getTipoDocto().setAcronimo(ent.getTipoDocumento().getAcronimo());
		}
		if (LOGGER.isInfoEnabled()) {
			final long timeFin = System.currentTimeMillis();
			LOGGER.info("Fin - consultar() en " + (timeFin - timeIni) + " ms");
		}
		dto.setNumeroVersion(ent.getNumeroVersion());
		return dto;
	}

	@Override
	public ArchivoRestDTO versionar(VersionArchivoInRestDTO archDto) throws CMException {
		final long timeIni = System.currentTimeMillis();
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Iniciar - versionar()");
		}
		if (archDto == null || !DTOUtils.isValid(archDto.getOriginal())) {
			throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO, "El ID de Archivo Origen es requerido");
		}
		validarArchivo(archDto.getArchivo());
		final Optional<Archivo> optOld = this.archivoDao.findById(archDto.getOriginal().getId());
		final Archivo oldFile = optOld.isPresent() ? optOld.get() : null;

		if (oldFile == null) {
			throw new NegocioException(ErrorCodes.NEGOCIO_ARCHIVO_NO_EXISTE,
					"El ID de Archivo proporcionado no existe");
		}
		if (BooleanUtils.isTrue(oldFile.getObsoleto())) {
			throw new NegocioException(ErrorCodes.NEGOCIO_ESTADO_INCORRECTO,
					"El Archivo ya es obsoleto, debe versionar la versi\u00F3n vigente");
		}
		oldFile.setObsoleto(true);

		final Archivo newVersionFile = new Archivo();
		poblarContenido(archDto.getArchivo(), newVersionFile);
		newVersionFile.setComentario(archDto.getArchivo().getComentario());
		newVersionFile.setExpediente(oldFile.getExpediente());
		newVersionFile.setFechaRegistro(UtilDates.getCurrentDate());
		oldFile.setFechaModificacion(newVersionFile.getFechaModificacion());
		newVersionFile.setNombre(archDto.getArchivo().getNombre());
		Integer oldVersionNum = oldFile.getNumeroVersion();
		if (oldVersionNum == null) {
			oldFile.setNumeroVersion(1);
			newVersionFile.setNumeroVersion(2);
		} else {
			newVersionFile.setNumeroVersion(++oldVersionNum);
		}
		newVersionFile.setOriginal(oldFile);
		newVersionFile.setTipo(new TipoMultimedia(archDto.getArchivo().getTipoMM().getId()));
		newVersionFile.setTipoDocumento(oldFile.getTipoDocumento());

		this.archivoDao.save(newVersionFile);
		this.archivoDao.update(oldFile);
		final ArchivoRestDTO salida = new ArchivoRestDTO();
		salida.setId(newVersionFile.getId());
		salida.setNumeroVersion(newVersionFile.getNumeroVersion());
		salida.setIdOriginal(oldFile.getId());
		if (LOGGER.isInfoEnabled()) {
			final long timeFin = System.currentTimeMillis();
			LOGGER.info("Fin - versionar() en " + (timeFin - timeIni) + " ms");
		}
		return salida;
	}

	@Override
	public ArchivoRestDTO reemplazar(VersionArchivoInRestDTO archDto) throws CMException {
		final long timeIni = System.currentTimeMillis();
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Iniciar - reemplazar()");
		}
		if (archDto == null || !DTOUtils.isValid(archDto.getOriginal())) {
			throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO, "El ID de Archivo Origen es requerido");
		}
		validarArchivo(archDto.getArchivo());
		final Optional<Archivo> optOld = this.archivoDao.findById(archDto.getOriginal().getId());
		final Archivo fromBD = optOld.isPresent() ? optOld.get() : null;
		if (fromBD == null) {
			throw new NegocioException(ErrorCodes.NEGOCIO_ARCHIVO_NO_EXISTE,
					"El ID de Archivo proporcionado no existe");
		}
		if (BooleanUtils.isTrue(fromBD.getObsoleto())) {
			throw new NegocioException(ErrorCodes.NEGOCIO_ESTADO_INCORRECTO,
					"El Archivo ya es obsoleto, debe reemplazar la versi\u00F3n vigente");
		}
		poblarContenido(archDto.getArchivo(), fromBD);
		fromBD.setNombre(archDto.getArchivo().getNombre());
		fromBD.setComentario(archDto.getArchivo().getComentario());
		fromBD.setTipo(new TipoMultimedia(archDto.getArchivo().getTipoMM().getId()));
		fromBD.setFechaModificacion(UtilDates.getCurrentDate());
		this.archivoDao.update(fromBD);
		final ArchivoRestDTO salida = new ArchivoRestDTO();
		salida.setId(fromBD.getId());
		salida.setNumeroVersion(fromBD.getNumeroVersion());
		if (LOGGER.isInfoEnabled()) {
			final long timeFin = System.currentTimeMillis();
			LOGGER.info("Fin - reemplazar() en " + (timeFin - timeIni) + " ms");
		}
		return salida;
	}

	@Override
	public List<ArchivoRestDTO> consultarVersiones(ArchivoLeanRestDTO in) throws CMException {
		final long timeIni = System.currentTimeMillis();
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Iniciar - consultarVersiones()");
		}
		final Archivo prevOnLyID = this.archivoDao.consultarById(in.getId());
		final List<ArchivoRestDTO> acumulado = new ArrayList<ArchivoRestDTO>();
		this.recuperarVersion(prevOnLyID, acumulado);
		if (LOGGER.isInfoEnabled()) {
			final long timeFin = System.currentTimeMillis();
			LOGGER.info("Fin - consultarVersiones() en " + (timeFin - timeIni) + " ms");
		}
		return acumulado;
	}

	void recuperarVersion(Archivo ultma, List<ArchivoRestDTO> acumulado) {
		if (ultma != null) {
			LOGGER.info("ultma.getId() :: "+ultma.getId());
			final Archivo prevOnLyID = this.consultarOrignal(ultma);
			LOGGER.info("acumulado.size() :: "+acumulado.size());
			if (prevOnLyID != null) {
				final Archivo previo = this.archivoDao.consultarById(prevOnLyID.getId());
				LOGGER.info("Poblando el " + previo);
				final ArchivoRestDTO dto = new ArchivoRestDTO(previo.getId());
				dto.setComentario(previo.getComentario());
				dto.setNombre(previo.getNombre());
				acumulado.add(dto);
				recuperarVersion(previo, acumulado);
			}
		}
	}

	private Archivo consultarOrignal(Archivo ultima) {
		LOGGER.info("Buscando original para " + ultima.getId());
		final List<Archivo> data = this.archivoDao.consultarOrignal(ultima.getId());
		if (CollectionUtils.isNotEmpty(data)) {
			final Archivo found = data.get(0);
			if (found.getOriginal() != null) {
				LOGGER.info("Encontrado :: " + found.getOriginal().getId());
				return found.getOriginal();
			} else {
				LOGGER.info("Sin Original");
			}
		}
		return null;
	}

	@Override
	public ArchivoRestDTO elimiar(ArchivoLeanRestDTO in) throws CMException {
		final long timeIni = System.currentTimeMillis();
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Iniciar - elimiar()");
		}
		final Optional<Archivo> optOld = this.archivoDao.findById(in.getId());
		final Archivo ent = optOld.isPresent() ? optOld.get() : null;
		if (ent == null) {
			LOGGER.warn("Archivo " + in.getId() + " no encontrado");
			throw new NegocioException(ErrorCodes.NEGOCIO_ARGUMENTO_INCORRECTO,
					"El archivo proporcionado no existe en la base de datos");
		}
		if (BooleanUtils.isTrue(ent.getObsoleto())) {
			throw new NegocioException(ErrorCodes.NEGOCIO_ESTADO_INCORRECTO,
					"El Archivo ya es obsoleto, debe eliminiar la versi\u00F3n vigente");
		}
		final ArchivoRestDTO dto = new ArchivoRestDTO(ent.getId());
		ent.setIndActivo(Boolean.FALSE);
		this.archivoDao.update(ent);

		dto.setFechaRegistro(ent.getFechaRegistro());
		dto.setHashMd5(ent.getHashMd5());
		dto.setNombre(ent.getNombre());
		dto.setComentario(ent.getComentario());

		dto.setTipoMM(new TipoMultimediaRestDTO(ent.getTipo().getId()));
		dto.getTipoMM().setMimeType(ent.getTipo().getMimeType());
		dto.getTipoMM().setExtension(ent.getTipo().getExtension());
		if (ent.getTipoDocumento() != null) {
			dto.setTipoDocto(
					new TipoDocumentoRestDTO(ent.getTipoDocumento().getId(), ent.getTipoDocumento().getNombre()));
			dto.getTipoDocto().setAcronimo(ent.getTipoDocumento().getAcronimo());
		}
		if (LOGGER.isInfoEnabled()) {
			final long timeFin = System.currentTimeMillis();
			LOGGER.info("Fin - elimiar() en " + (timeFin - timeIni) + " ms");
		}
		dto.setNumeroVersion(ent.getNumeroVersion());
		return dto;
	}
}
