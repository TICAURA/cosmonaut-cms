/**
 * 
 */
package com.grupoauramexico.cm.commons.utils;

import java.text.MessageFormat;

/**
 * @author vaguire
 *
 */
public class MsgUtils {
	final static MessageFormat FMT_LONGITUD = new MessageFormat(
			"La longitud del atributo {1}.{0} no debe superar los {2} caracteres.");

	/**
	 * 
	 */
	public MsgUtils() {
	}

	public static void main(String[] args) {
		Object[] params = { new Long(2), "otro" };

		MessageFormat fmt = new MessageFormat("String is \"{1}\", number is {0}.");
		System.out.println(fmt.format(params));

	}
	/**
	 * Genera un mensaje
	 * @param campo
	 * @param entidad
	 * @param longitd
	 * @return
	 */
	public static String longitud(String campo, String entidad, Integer longitd) {
		Object[] params = { campo, entidad, longitd };
		return FMT_LONGITUD.format(params);
	}
}
