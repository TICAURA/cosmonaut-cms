/**
 * 
 */
package com.grupoauramexico.cm.commons.utils;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;



/**
 * @author Vladimir Aguirre Piedragil
 *
 */
public class TokenGenerator {
//	/**
//	 * 
//	 * @param cabecero
//	 * @return
//	 * @throws USException
//	 */
//	public final static String generarToken(final CabeceroRestDTO cabecero) throws USException {
//		final StringBuilder data = new StringBuilder();
//		data.append(UUID.randomUUID().toString());
//		if (cabecero != null) {
//			if (cabecero.getApiKey() != null) {
//				data.append(cabecero.getApiKey());
//			}
//			if (cabecero.getIdCustomer() != null) {
//				data.append(cabecero.getIdCustomer());
//			}
//		}
//		data.append(UtilDates.getCurrentDate().toString());
//		final String tokenOperacion = DigestUtils.shaHex(data.toString());
//		return tokenOperacion;
//	}

	/**
	 * 
	 * @return
	 * @throws USException
	 */
	public final static String generarToken()  {
		final StringBuilder data = new StringBuilder();
		data.append(UUID.randomUUID().toString());

		final String tokenOperacion = DigestUtils.sha1Hex(data.toString());
		return tokenOperacion;
	}

}
