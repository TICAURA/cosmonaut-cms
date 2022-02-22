/**
 * 
 */
package com.grupoauramexico.cm.controller.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.grupoauramexico.cm.commons.enums.ErrorCodes;
import com.grupoauramexico.cm.commons.exception.CMException;
import com.grupoauramexico.cm.commons.utils.TokenGenerator;
import com.grupoauramexico.cm.dto.base.BaseOutRestDTO;
import com.grupoauramexico.cm.dto.base.ErrorRestDTO;

import io.micronaut.http.HttpResponse;
import io.reactivex.Single;

/**
 * @author vaguire
 *
 */
public abstract class BaseRestController {
	private static final Logger LOG = LoggerFactory.getLogger(BaseRestController.class);

	/**
	 * 
	 */
	public BaseRestController() {
	}

	protected Single<HttpResponse<? extends BaseOutRestDTO>> armarErrorResponse(Exception exception) {
		final String tokenOp = TokenGenerator.generarToken();
		LOG.error(tokenOp + " -> " + exception.getMessage());
		ErrorRestDTO dto = null;
		if (exception instanceof NullPointerException) {
			dto = new ErrorRestDTO(new CMException(ErrorCodes.GENERICO_INTERNO, exception), false, tokenOp);
		} else {

			dto = new ErrorRestDTO(new CMException(ErrorCodes.GENERICO_INTERNO, exception), true, tokenOp);
		}
		return Single.just(HttpResponse.serverError(dto));
	}

	/**
	 * 
	 * @param exception
	 * @return
	 */
	protected Single<HttpResponse<? extends BaseOutRestDTO>> armarErrorResponse(CMException exception) {
		final String tokenOp = TokenGenerator.generarToken();
		LOG.error(tokenOp + " -> " + exception.getMessage());
		final ErrorRestDTO dto = new ErrorRestDTO(exception, true, tokenOp);

		return Single.just(HttpResponse.serverError(dto));

	}

	protected <T> T armarErrorResponse(Class<T> tipo, CMException exception) {
		final String tokenOp = TokenGenerator.generarToken();
		LOG.error(tokenOp + " -> " + exception.getMessage());
		final ErrorRestDTO dto = new ErrorRestDTO(exception, true, tokenOp);
		return (T) dto;
	}

	protected <T> T armarErrorResponse(Class<T> tipo, Exception exception) {
		final String tokenOp = TokenGenerator.generarToken();
		LOG.error(tokenOp + " -> " + exception.getMessage());
		ErrorRestDTO dto =null;
			if (exception instanceof NullPointerException) {
				dto = new ErrorRestDTO(new CMException(ErrorCodes.GENERICO_INTERNO, exception), false,
						tokenOp);
			} else {
				
				dto =new ErrorRestDTO(new CMException(ErrorCodes.GENERICO_INTERNO, exception), true,
					tokenOp);
			}
		return (T) dto;
	}
	
	
	protected void poblarErrorResponse(BaseOutRestDTO dto, CMException exception) {
		final String tokenOp = TokenGenerator.generarToken();
		LOG.error(tokenOp + " -> " + exception.getCodigo() + ": " + exception.getMessage());
		final ErrorRestDTO dtoerr = new ErrorRestDTO(exception, true, tokenOp);
		dto.setResponseStatus(dtoerr.getResponseStatus());
	}

	protected void poblarErrorResponse(BaseOutRestDTO dto, Exception exception) {
		final String tokenOp = TokenGenerator.generarToken();
		LOG.error(tokenOp + " -> " + exception.getMessage());
		ErrorRestDTO dtoerr = null;
		if (exception instanceof NullPointerException) {
			dtoerr = new ErrorRestDTO(new CMException(ErrorCodes.GENERICO_INTERNO, exception), false, tokenOp);
		} else {

			dtoerr = new ErrorRestDTO(new CMException(ErrorCodes.GENERICO_INTERNO, exception), true, tokenOp);
		}
		dto.setResponseStatus(dtoerr.getResponseStatus());
	}
	
}
