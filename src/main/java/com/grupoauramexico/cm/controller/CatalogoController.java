/**
 * 
 */
package com.grupoauramexico.cm.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.grupoauramexico.cm.commons.exception.CMException;
import com.grupoauramexico.cm.controller.base.BaseRestController;
import com.grupoauramexico.cm.dto.CatTipoDocumentoRestDTO;
import com.grupoauramexico.cm.dto.TipoMultimediaRestDTO;
import com.grupoauramexico.cm.dto.in.CreacionMultimediaInRestDTO;
import com.grupoauramexico.cm.dto.in.CreacionTipoDocumentoInRestDTO;
import com.grupoauramexico.cm.dto.out.ListaMultimediasOutRestDTO;
import com.grupoauramexico.cm.dto.out.ListaTiposDocumentosOutRestDTO;
import com.grupoauramexico.cm.dto.out.MultimediaOutRestDTO;
import com.grupoauramexico.cm.dto.out.TipoDocumentosOutRestDTO;
import com.grupoauramexico.cm.service.CatalogoService;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.reactivex.Single;

/**
 * @author vaguire
 *
 */
@Controller("/cats/")
public class CatalogoController extends BaseRestController{
	private static final Logger LOG = LoggerFactory.getLogger(CatalogoController.class);

	@Inject
	private CatalogoService catService;

	/**
	 * Consulta de catalogo de tipos de multimedias soportados por el cm
	 * @return Lista de tipos de archivos (mimeTypes)
	 */
	@Get(uri = "/multimedias", produces = MediaType.APPLICATION_JSON)
	public Single<HttpResponse<ListaMultimediasOutRestDTO>> consultaMultimedias() {
		final ListaMultimediasOutRestDTO out = new ListaMultimediasOutRestDTO();
		out.setData(catService.getMultimedias());
		return Single.just(HttpResponse.ok(out));
	}
	/**
	 * Consulta de catalogo de tipos de dpcumentos soportados por el cm
	 * @return Lista de tipos de documentos (mimeTypes)
	 */
	@Get(uri = "/tiposDocumentos", produces = MediaType.APPLICATION_JSON)
	public Single<HttpResponse<ListaTiposDocumentosOutRestDTO>> consultaTiposDocumentos() {
		final ListaTiposDocumentosOutRestDTO out = new ListaTiposDocumentosOutRestDTO();
		out.setData(catService.getTiposDocumentos());
		return Single.just(HttpResponse.ok(out));
	}
	
	/**
	 * Crea un nuevo tipo de multimedia
	 * @param entrada La data para crear un nuevo registros de tipo multimedia: extension y mimeType
	 * @return El id de multimedias recien creado
	 */
	@Post(uri = "/multimedias/crear", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Single<HttpResponse<MultimediaOutRestDTO>> crearMultimedia(final CreacionMultimediaInRestDTO entrada) {
		final MultimediaOutRestDTO out = new MultimediaOutRestDTO();
		try {
			final TipoMultimediaRestDTO resp = catService.crear(entrada.getData());
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
	 * Crea un nuevo tipo de documento
	 * @param entrada La data completa del tipo de documento
	 * @return El id del tipo de documento recien creado
	 */
	@Post(uri = "/tiposDocumentos/crear", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Single<HttpResponse<TipoDocumentosOutRestDTO>> crearTipoDoc(final CreacionTipoDocumentoInRestDTO entrada) {
		final TipoDocumentosOutRestDTO out = new TipoDocumentosOutRestDTO();
		try {
			final CatTipoDocumentoRestDTO resp = catService.crear(entrada.getData());
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
	 * Modifica un nuevo tipo de documento
	 * @param entrada
	 * @return EL id del tipo de documento recien creado
	 */
	@Post(uri = "/tiposDocumentos/modificar", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Single<HttpResponse<TipoDocumentosOutRestDTO>> modificarTipoDoc(final CreacionTipoDocumentoInRestDTO entrada) {
		final TipoDocumentosOutRestDTO out = new TipoDocumentosOutRestDTO();
		try {
			final CatTipoDocumentoRestDTO resp = catService.modificar(entrada.getData());
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
	 * Elimina (de manera logica) el tipo de documento
	 * @param entrada
	 * @return EL id del tipo de documento recien eliminado
	 */
	@Post(uri = "/tiposDocumentos/eliminar", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Single<HttpResponse<TipoDocumentosOutRestDTO>> eliminarTipoDoc(final CreacionTipoDocumentoInRestDTO entrada) {
		final TipoDocumentosOutRestDTO out = new TipoDocumentosOutRestDTO();
		try {
			final CatTipoDocumentoRestDTO resp = catService.eliminar(entrada.getData());
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
	 * Activa el tipo de documento
	 * @param entrada
	 * @return EL id del tipo de documento recien activado
	 */
	@Post(uri = "/tiposDocumentos/activar", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Single<HttpResponse<TipoDocumentosOutRestDTO>> activarTipoDoc(final CreacionTipoDocumentoInRestDTO entrada) {
		final TipoDocumentosOutRestDTO out = new TipoDocumentosOutRestDTO();
		try {
			final CatTipoDocumentoRestDTO resp = catService.eliminar(entrada.getData());
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
