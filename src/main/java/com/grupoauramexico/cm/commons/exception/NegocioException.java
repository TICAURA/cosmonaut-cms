/**
 * 
 */
package com.grupoauramexico.cm.commons.exception;

import com.grupoauramexico.cm.commons.enums.ErrorCodes;

/**
 * @author vaguire
 *
 */
public class NegocioException extends CMException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1855665840595665955L;

	/**
	 * 
	 */
	public NegocioException() {
	}

	/**
	 * @param codigo
	 */
	public NegocioException(ErrorCodes codigo) {
		super(codigo);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param code
	 * @param mensaje
	 * @param causa
	 */
	public NegocioException(ErrorCodes code, String mensaje, Throwable causa) {
		super(code, mensaje, causa);
	}
	/**
	 * @param code
	 * @param mensaje
	 * @param causa
	 */
	public NegocioException(ErrorCodes code, String mensaje) {
		super(code, mensaje);
	}
	/**
	 * @param code
	 * @param causa
	 */
	public NegocioException(ErrorCodes code, Throwable causa) {
		super(code, causa);
	}

	/**
	 * @param arg0
	 */
	public NegocioException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public NegocioException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public NegocioException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public NegocioException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
