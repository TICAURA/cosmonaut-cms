/**
 * 
 */
package com.grupoauramexico.cm.dto.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * * Clase para dar soporte a las consultas paginadas. <br>
 * <code>registrosPagina</code> indica el número de registros por página.
 * 
 * @author vladimir.aguirre
 *
 */
public class PaginaDTOGeneric<D extends BaseRestDTO> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7898887112858144844L;

	private int MAX_PAGES_DISPLAYED = 10;
	/**
	 * Número de registros por página. <br>
	 * El valor default es <b>10</b>.
	 */
	private Integer ROWS_BY_PAGE = 10;
	private Long totalRegistros = Long.valueOf(0);
	private Integer totalPaginas = Integer.valueOf(0);
	private List<Integer> indice = new ArrayList<Integer>();
	private Integer paginaActual = Integer.valueOf(0);
	private Integer inicioPagina = null;

	private Integer primeraPagina = Integer.valueOf(0);;
	private Integer ultimaPagina = Integer.valueOf(0);;
	private Integer registrosPagina = ROWS_BY_PAGE;

	/**
	 * Para cuando se usa el LazyDataModel de <i>primefaces</i> (org.primefaces.model.LazyDataModel)
	 */
	private boolean busquedaLDModel= false;
	private String order;
	private boolean asc;
	/**
	 * Indica si la consulta es paginada o no.
	 */
	private boolean consultarTodos = false;
	/**
	 * Objeto para el filtro, usado para armar el <code>query</code>
	 */
	private D filtro;
	private String fullSearch;
	/**
	 * Si es <code>false</code> pueden usar <code>fullSearch</code>, si es
	 * <code>true</code> deben obligatoriamente usar las propiedades de
	 * <code>filtro</code>
	 */
	private boolean advancedSearch = false;

	/**
	 * Lista que contiene la información, exclusivamente la pagina a mostrar.
	 */
	private List<D> datos;

	private long tiempoEjecucion = 0;

	/**
	 * Número de páginas desplegadas en el índice de la páginación. <br>
	 * El valor default es <b>5</b>.
	 */

	/**
	 * 
	 */
	public PaginaDTOGeneric() {
		this.consultarTodos = false;
		this.paginaActual = Integer.valueOf(1);
	}

	public void addDto(D dto) {
		if (datos == null) {
			datos = new ArrayList<D>();
		}
		datos.add(dto);
	}

	public void addDtos(List<D> dtos) {
		if (datos == null) {
			datos = new ArrayList<D>();
		}
		if (dtos != null) {
			datos.addAll(dtos);
		}
	}

	/**
	 * Para limpiar los resultados en la pantalla
	 */
	public void resetDatos() {
		datos = new ArrayList<D>();
		totalRegistros = Long.valueOf(0);
		totalPaginas = Integer.valueOf(0);
		this.paginaActual = Integer.valueOf(1);
	}

	/**
	 * @return the totalRegistros
	 */
	public Long getTotalRegistros() {
		return totalRegistros;
	}

	/**
	 * @param totalRegistros the totalRegistros to set
	 */
	public void setTotalRegistros(Long totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	/**
	 * @return the totalPaginas
	 */
	public Integer getTotalPaginas() {
		return totalPaginas;
	}

	/**
	 * @param totalPaginas the totalPaginas to set
	 */
	public void setTotalPaginas(Integer totalPaginas) {
		this.totalPaginas = totalPaginas;
	}

	/**
	 * @return the indice
	 */
	public List<Integer> getIndice() {
		return indice;
	}

	/**
	 * @param indice the indice to set
	 */
	public void setIndice(List<Integer> indice) {
		this.indice = indice;
	}

	/**
	 * @return the paginaActual
	 */
	public Integer getPaginaActual() {
		return paginaActual;
	}

	/**
	 * @param paginaActual the paginaActual to set
	 */
	public void setPaginaActual(Integer paginaActual) {
		this.paginaActual = paginaActual;
	}

	/**
	 * @return the primeraPagina
	 */
	public Integer getPrimeraPagina() {
		calcularPaginas();
		return primeraPagina;
	}

	public void calcularPaginas() {
		primeraPagina = 1;
		ultimaPagina = totalPaginas;
		indice = new ArrayList<Integer>();
		int pos = 0;
		int inicio = 1;
		boolean inLimiteSuperior = false;
		if (paginaActual + (MAX_PAGES_DISPLAYED / 2) > ultimaPagina) {
			inicio = ultimaPagina - MAX_PAGES_DISPLAYED;
			inicio++;
			inLimiteSuperior = true;
		}
		if (inicio < 1) {
			inicio = 1;
		} else {
			if (!inLimiteSuperior) {
				inicio = paginaActual - (MAX_PAGES_DISPLAYED / 2) > 0 ? paginaActual - (MAX_PAGES_DISPLAYED / 2) : 1;
			}
		}
		while (inicio <= totalPaginas && pos < MAX_PAGES_DISPLAYED) {
			indice.add(inicio);
			pos++;
			inicio++;
		}
	}

	/**
	 * @param primeraPagina the primeraPagina to set
	 */
	public void setPrimeraPagina(Integer primeraPagina) {
		this.primeraPagina = primeraPagina;
	}

	/**
	 * @return the ultimaPagina
	 */
	public Integer getUltimaPagina() {
		return ultimaPagina;
	}

	/**
	 * @param ultimaPagina the ultimaPagina to set
	 */
	public void setUltimaPagina(Integer ultimaPagina) {
		this.ultimaPagina = ultimaPagina;
	}

	/**
	 * @return the registrosPagina
	 */
	public Integer getRegistrosPagina() {
		return registrosPagina;
	}

	/**
	 * @param registrosPagina the registrosPagina to set
	 */
	public void setRegistrosPagina(Integer registrosPagina) {
		this.registrosPagina = registrosPagina;
	}

	/**
	 * @return the consultarTodos
	 */
	public boolean isConsultarTodos() {
		return consultarTodos;
	}

	/**
	 * @param consultarTodos the consultarTodos to set
	 */
	public void setConsultarTodos(boolean consultarTodos) {
		this.consultarTodos = consultarTodos;
	}

	/**
	 * @return the filtro
	 */
	public D getFiltro() {
		return filtro;
	}

	/**
	 * @param filtro the filtro to set
	 */
	public void setFiltro(D filtro) {
		this.filtro = filtro;
	}

	/**
	 * @return the datos
	 */
	public List<D> getDatos() {
		return datos;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDatos(List<D> datos) {
		this.datos = datos;
	}

	/**
	 * @return the fullSearch
	 */
	public String getFullSearch() {
		return fullSearch;
	}

	/**
	 * @param fullSearch the fullSearch to set
	 */
	public void setFullSearch(String fullSearch) {
		this.fullSearch = fullSearch;
	}

	@Override
	public String toString() {
		final StringBuilder cad = new StringBuilder();
		cad.append("filtro=[").append(filtro).append("]");
		cad.append(", fullSearch=[").append(fullSearch).append("]");
		cad.append(", consultarTodos=").append(consultarTodos);
		cad.append(", registrosPagina=").append(registrosPagina);
		cad.append(", totalRegistros=").append(totalRegistros);
		if (datos != null) {
			cad.append(", datos.size=").append(datos.size());
		} else {
			cad.append(", datos is null");
		}
		cad.append(", totalPaginas=").append(totalPaginas);
		cad.append(", paginaActual=").append(paginaActual);
		cad.append(", primeraPagina=").append(primeraPagina);
		cad.append(", ultimaPagina=").append(ultimaPagina);
		cad.append(", tiempoEjecucion=").append(tiempoEjecucion);

		return cad.toString();
	}

	/**
	 * @return the tiempoEjecucion
	 */
	public long getTiempoEjecucion() {
		return tiempoEjecucion;
	}

	/**
	 * @param tiempoEjecucion the tiempoEjecucion to set
	 */
	public void setTiempoEjecucion(long tiempoEjecucion) {
		this.tiempoEjecucion = tiempoEjecucion;
	}

	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * @return the asc
	 */
	public boolean isAsc() {
		return asc;
	}

	/**
	 * @param asc the asc to set
	 */
	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	/**
	 * @return the advancedSearch
	 */
	public boolean isAdvancedSearch() {
		return advancedSearch;
	}

	/**
	 * @param advancedSearch the advancedSearch to set
	 */
	public void setAdvancedSearch(boolean advancedSearch) {
		this.advancedSearch = advancedSearch;
	}

	/**
	 * @return the inicioPagina
	 */
	public Integer getInicioPagina() {
		return inicioPagina;
	}

	/**
	 * @param inicioPagina the inicioPagina to set
	 */
	public void setInicioPagina(Integer inicioPagina) {
		this.inicioPagina = inicioPagina;
	}

	/**
	 * @return the busquedaLDModel
	 */
	public boolean isBusquedaLDModel() {
		return busquedaLDModel;
	}

	/**
	 * @param busquedaLDModel the busquedaLDModel to set
	 */
	public void setBusquedaLDModel(boolean busquedaLDModel) {
		this.busquedaLDModel = busquedaLDModel;
	}

}
