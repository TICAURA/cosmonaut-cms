/**
 * 
 */
package com.grupoauramexico.cm.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.grupoauramexico.cm.commons.exception.CMException;
import com.grupoauramexico.cm.controller.base.BaseRestController;
import com.grupoauramexico.cm.dto.ExpedienteLeanRestDTO;
import com.grupoauramexico.cm.dto.ExpedienteRestDTO;
import com.grupoauramexico.cm.dto.in.CreacionExpedienteInRestDTO;
import com.grupoauramexico.cm.dto.out.ExpedienteOutRestDTO;
import com.grupoauramexico.cm.service.ExpedienteService;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.reactivex.Single;
import io.swagger.v3.oas.annotations.Operation;

/**
 * @author vaguire
 *
 */
@Controller("/expediente/")
public class ExpedienteController extends BaseRestController {
	private static final Logger LOG = LoggerFactory.getLogger(ExpedienteController.class);

	@Inject
	private ExpedienteService expService;

	/**
	 * 
	 */
	public ExpedienteController() {
	}

	/**
	 * 
	 * @param page Datos del expediente a consultar, puede ser por id o por clave
	 * @return El detalle del expediente
	 */
	@Operation(summary = "Consulta un expediente", description = "Consulta los datos completos de un expediente")
	@Get(uri = "/detalle{?page*}", produces = MediaType.APPLICATION_JSON)
	public Single<HttpResponse<ExpedienteOutRestDTO>> consultarExpedienteDetalle(
			@Valid @Nullable ExpedienteLeanRestDTO page) {
		final ExpedienteOutRestDTO out = new ExpedienteOutRestDTO();
		try {
			LOG.info("expService :: " + expService);
			final ExpedienteRestDTO expDto = this.expService.consultar(page);
			LOG.info("OK ");

			out.setData(expDto);
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
	 * Crea un nuevo expediente
	 * 
	 * @param entrada Los datos del expediente a guardar. Unidad=Unidad de negocio.
	 *                Evento=Evento de negocio que genera el expediente.
	 * 
	 * @return El expediente con el id y clave recien creado.
	 */
	@Operation(summary = "Crea un nuevo expediente", description = "Creacion de un expediente. Si clave tiene valor se respeta y se guarda con ese valor. En caso contrario se calcula")
	@Post(uri = "/crear", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Single<HttpResponse<ExpedienteOutRestDTO>> crearExpediente(final CreacionExpedienteInRestDTO entrada) {
		final ExpedienteOutRestDTO out = new ExpedienteOutRestDTO();
		try {
			final ExpedienteRestDTO resp = expService.crear(entrada);
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
