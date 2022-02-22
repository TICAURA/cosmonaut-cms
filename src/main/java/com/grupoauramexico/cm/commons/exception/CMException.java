/**
 * 
 */
package com.grupoauramexico.cm.commons.exception;

import com.grupoauramexico.cm.commons.enums.ErrorCodes;

/**
 * @author vaguire
 *
 */
public class CMException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3245783197889652936L;
	private ErrorCodes codigo;

	/**
	 * 
	 */
	public CMException() {
	}

	/**
	 * 
	 * @param code
	 * @param mensaje
	 * @param causa
	 */
	public CMException(ErrorCodes code, String mensaje, Throwable causa) {
		super(code.concatCodeDesc(mensaje), causa);
		this.codigo = code;
	}

	/**
	 * @param codigo
	 */
	public CMException(ErrorCodes codigo) {
		super(codigo.getDescription());
		this.codigo = codigo;
	}

	public CMException(ErrorCodes code, String mensaje) {
		super(code.concatCodeDesc(mensaje));
		this.codigo = code;
	}

	/**
	 * 
	 * @param code
	 * @param causa
	 */
	public CMException(ErrorCodes code, Throwable causa) {
		super(code.getCodeDesc(), causa);
		this.codigo = code;

	}

	/**
	 * @param arg0
	 */
	public CMException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public CMException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public CMException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public CMException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the codigo
	 */
	public ErrorCodes getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(ErrorCodes codigo) {
		this.codigo = codigo;
	}

}
