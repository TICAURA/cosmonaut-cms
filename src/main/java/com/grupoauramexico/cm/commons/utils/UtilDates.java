/**
 * 
 */
package com.grupoauramexico.cm.commons.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateUtils;

/**
 * @author Vladimir Aguirre Piedragil
 *
 */
public class UtilDates {
	/**
	 * dd/MM/yyyy
	 */
	private final static SimpleDateFormat SDF_SHORT = new SimpleDateFormat("dd/MM/yyyy");
	/**
	 * dd/MM/yyyy HH:mm
	 */
	private final static SimpleDateFormat SDF_SHORT_HORA = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	private final static SimpleDateFormat SDF_SHORT_HORA_2Y = new SimpleDateFormat("dd/MM/yy HH:mm");
	private final static SimpleDateFormat SDF_FIRMA = new SimpleDateFormat("dd/MM/yy'T'HH:mm:ss");

	/**
	 * dd/MM/yyyy HH:mm a
	 */
	private final static SimpleDateFormat SDF_SHORT_HORA_A = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
	/**
	 * "d 'de' MMMM 'del' yyyy"
	 */
	private final static SimpleDateFormat SDF_SHORT_REPORTES = new SimpleDateFormat("d 'de' MMMM 'del' yyyy");

	/**
	 * America/Mexico_City
	 * 
	 * @return
	 */
	public static Calendar getCurrentTimeStamp() {
		return Calendar.getInstance(TimeZone.getTimeZone("America/Mexico_City"));
	}

	/**
	 * America/Mexico_City
	 * 
	 * @return
	 */
	public static LocalDate getLocalDate() {
		return LocalDate.now(ZoneId.of("America/Mexico_City"));
	}

	/**
	 * Obtiene la fecha actual America/Mexico_City
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		return getCurrentTimeStamp().getTime();
	}

	public static String getCurrentDateCorto() {
		return SDF_SHORT.format(getCurrentDate());
	}

	/**
	 * dd/MM/yyyy HH:mm a
	 * 
	 * @return
	 */
	public static String getCurrentDateCortoHora() {
		return SDF_SHORT_HORA_A.format(getCurrentDate());
	}

	/**
	 * dd/MM/yyyyTHH:mm:ss
	 * 
	 * @return
	 */
	public static String getCurrentDateFE() {
		return SDF_FIRMA.format(getCurrentDate());
	}

	/**
	 * dd/MM/yyyy
	 * 
	 * @param dat
	 * @return
	 */
	public static String formatCorto(Date dat) {
		if (dat != null) {
			return SDF_SHORT.format(dat);
		}
		return null;
	}

	public static String formatCortoTS(Date dat) {
		if (dat != null) {
			final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(dat);
		}
		return null;
	}

	/**
	 * dd/MM/yy HH:mm
	 * 
	 * @param dat
	 * @return
	 */
	public static String formatCortoHora2y(Date dat) {
		if (dat != null) {
			return SDF_SHORT_HORA_2Y.format(dat);
		}
		return null;
	}

	/**
	 * dd/MM/yyyy HH:mm
	 * 
	 * @param dat
	 * @return
	 */
	public static String formatCortoHora(Date dat) {
		if (dat != null) {
			return SDF_SHORT_HORA.format(dat);
		}
		return null;
	}

	/**
	 * dd/MM/yyyy HH:mm a
	 * 
	 * @param dat
	 * @return
	 */
	public static String formatCortoHoraa(Date dat) {
		if (dat != null) {
			return SDF_SHORT_HORA_A.format(dat);
		}
		return null;
	}

	public static String getSdfShortReportes() {
		return SDF_SHORT_REPORTES.format(getCurrentDate());
	}

	public static String formatReporte(Date dat) {
		if (dat != null) {
			return SDF_SHORT_REPORTES.format(dat);
		}
		return null;
	}

	/**
	 * Compara una fecha sin importar las horas, minutos ni segundos.
	 * 
	 * @param fechaAvalidar
	 * @param fechaComparacion
	 * @return un entero negativo, cero o positivo si <code>fechaAvalidar</code> es
	 *         menor, igual o mayor que <code>fechaComparacion</code>
	 * @throws IllegalArgumentException si algún argumento es <code>null</code>.
	 */
	public static int compararFechasSinHoras(Date fechaAvalidar, Date fechaComparacion) {
		return DateUtils.truncatedCompareTo(fechaAvalidar, fechaComparacion, Calendar.DATE);
	}

	/**
	 * Compara que dos fechas sean el mismo dia sin importar las horas, minutos ni
	 * segundos.
	 * 
	 * @param fechaAvalidar
	 * @param fechaComparacion
	 * @return
	 */
	public static boolean esMismoDia(Date fechaAvalidar, Date fechaComparacion) {
		return (DateUtils.truncatedCompareTo(fechaAvalidar, fechaComparacion, Calendar.DATE) == 0);
	}

	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date asDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDate asLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		// return LocalDate.ofInstant(
		// date.toInstant(), ZoneId.systemDefault());
		// return
		// Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		// LocalDate ld = new java.sql.Date(date.getTime()).toLocalDate();
		// return ld;
		// return
		// date.toInstant().atZone(ZoneId.of("America/Mexico_City")).toLocalDate();
	}

	public static LocalDateTime asLocalDateTime(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

}
