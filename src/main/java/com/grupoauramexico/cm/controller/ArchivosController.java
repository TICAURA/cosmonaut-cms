/**
 * 
 */
package com.grupoauramexico.cm.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.grupoauramexico.cm.commons.exception.CMException;
import com.grupoauramexico.cm.controller.base.BaseRestController;
import com.grupoauramexico.cm.dto.archivo.ArchivoLeanRestDTO;
import com.grupoauramexico.cm.dto.archivo.ArchivoRestDTO;
import com.grupoauramexico.cm.dto.in.UploadArchivoInRestDTO;
import com.grupoauramexico.cm.dto.in.VersionArchivoInRestDTO;
import com.grupoauramexico.cm.dto.out.ArchivoDescargaOutRestDTO;
import com.grupoauramexico.cm.dto.out.ArchivoOutRestDTO;
import com.grupoauramexico.cm.dto.out.ListaVersionesOutRestDTO;
import com.grupoauramexico.cm.service.ArchivosService;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.reactivex.Single;

/**
 * @author vaguire
 *
 */
@Controller("/archivos/")
public class ArchivosController extends BaseRestController {
	private static final Logger LOG = LoggerFactory.getLogger(ArchivosController.class);

	@Inject
	private ArchivosService archService;

	/**
	 * Agrega un archivo al expediente
	 * 
	 * @param entrada Info de archivo a cargar
	 * @return Retorna el id del archivo
	 */
	@Post(uri = "/upload", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Single<HttpResponse<ArchivoOutRestDTO>> upload(final UploadArchivoInRestDTO entrada) {
		ArchivoOutRestDTO out = new ArchivoOutRestDTO();
		try {
			final ArchivoRestDTO resp = archService.agregarArchivo(entrada);
			out.setData(resp);
			return Single.just(HttpResponse.ok(out));
		} catch (CMException e) {
			LOG.error(e.getMessage());
			super.poblarErrorResponse(out, e);
			return Single.just(HttpResponse.ok(out));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			super.poblarErrorResponse(out, e);
			return Single.just(HttpResponse.serverError(out));
		}
	}

	/**
	 * Reemplaza un archivo. Requiere original.id. Se conserva el tipo de documento.
	 * 
	 * @param entrada Info de archivo a reemplazar
	 * @return Retorna el id del archivo de entrada
	 */
	@Post(uri = "/replace", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Single<HttpResponse<ArchivoOutRestDTO>> replace(final VersionArchivoInRestDTO entrada) {
		ArchivoOutRestDTO out = new ArchivoOutRestDTO();
		try {
			final ArchivoRestDTO resp = archService.reemplazar(entrada);
			out.setData(resp);
			return Single.just(HttpResponse.ok(out));
		} catch (CMException e) {
			LOG.error(e.getMessage());
			super.poblarErrorResponse(out, e);
			return Single.just(HttpResponse.ok(out));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			super.poblarErrorResponse(out, e);
			return Single.just(HttpResponse.serverError(out));
		}
	}

	/**
	 * Genera una nueva version de un archivo. Requiere original.id. Se conserva el
	 * tipo de documento.
	 * 
	 * @param entrada Info de archivo a versionar. Debe existir en la BD y debe estar vigente
	 * @return Retorna el id del archivo de la nueva version
	 */
	@Post(uri = "/version", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Single<HttpResponse<ArchivoOutRestDTO>> version(final VersionArchivoInRestDTO entrada) {
		ArchivoOutRestDTO out = new ArchivoOutRestDTO();
		try {
			final ArchivoRestDTO resp = archService.versionar(entrada);
			out.setData(resp);
			return Single.just(HttpResponse.ok(out));
		} catch (CMException e) {
			LOG.error(e.getMessage());
			super.poblarErrorResponse(out, e);
			return Single.just(HttpResponse.ok(out));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			super.poblarErrorResponse(out, e);
			return Single.just(HttpResponse.serverError(out));
		}
	}

	/**
	 * Obtiene el archivo en base 64
	 * 
	 * @param idArch ID unico del archivo, proporcionado en la operacion upload. Debe existir en la BD y debe estar vigente
	 * @return La representacion del archivo en base64
	 */
	@Get(uri = "/download", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Single<HttpResponse<ArchivoDescargaOutRestDTO>> download(@QueryValue Long idArch) {
		ArchivoDescargaOutRestDTO out = new ArchivoDescargaOutRestDTO();
		try {
			final ArchivoRestDTO resp = archService.consultar(new ArchivoLeanRestDTO(idArch));
			out.setData(resp);
			return Single.just(HttpResponse.ok(out));
		} catch (CMException e) {
			LOG.error(e.getMessage());
			super.poblarErrorResponse(out, e);
			return Single.just(HttpResponse.ok(out));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			super.poblarErrorResponse(out, e);
			return Single.just(HttpResponse.serverError(out));
		}
	}

	/**
	 * Consulta de catalogo de tipos de multimedias soportados por el cm
	 * 
	 * @return Lista de tipos de archivos (mimeTypes)
	 */
	@Get(uri = "/versiones", produces = MediaType.APPLICATION_JSON)
	public Single<HttpResponse<ListaVersionesOutRestDTO>> versiones(@QueryValue Long idArch) {
		final ListaVersionesOutRestDTO out = new ListaVersionesOutRestDTO();
		try {
			out.setData(archService.consultarVersiones(new ArchivoLeanRestDTO(idArch)));
			return Single.just(HttpResponse.ok(out));
		} catch (CMException e) {
			LOG.error(e.getMessage());
			super.poblarErrorResponse(out, e);
			return Single.just(HttpResponse.ok(out));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			super.poblarErrorResponse(out, e);
			return Single.just(HttpResponse.serverError(out));
		}
	}
	
	/**
	 * Elimina un archivo del expediente
	 * @param idArch Id del archivoa borrar. Debe existir en la BD y debe estar vigente
	 * @return Los datos del archivo recien eliminado
	 */
	@Delete(uri = "/delete/{idArch}", produces = MediaType.APPLICATION_JSON)
	public Single<HttpResponse<ArchivoOutRestDTO>> delete(@PathVariable("idArch") Long idArch) {
		ArchivoOutRestDTO out = new ArchivoOutRestDTO();
		try {
			final ArchivoRestDTO resp = archService.elimiar(new ArchivoLeanRestDTO(idArch));
			out.setData(resp);
			return Single.just(HttpResponse.ok(out));
		} catch (CMException e) {
			LOG.error(e.getMessage());
			super.poblarErrorResponse(out, e);
			return Single.just(HttpResponse.ok(out));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			super.poblarErrorResponse(out, e);
			return Single.just(HttpResponse.serverError(out));
		}
	}
	
}
