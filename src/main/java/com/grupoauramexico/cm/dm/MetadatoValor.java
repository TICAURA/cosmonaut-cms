/**
 * 
 */
package com.grupoauramexico.cm.dm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Valor de <code>Cuadro<b>Metadato</b></code> para cada <code>Expediente</code>
 * 
 * @author Vladimir Aguirre
 *
 */
@Entity
@Table(name = "METADATO_VALOR")
public class MetadatoValor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4294948366352408721L;

	@Id
	@SequenceGenerator(name = "METADATO_VALOR_ID_GENERATOR", sequenceName = "METADATO_VALOR_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "METADATO_VALOR_ID_GENERATOR")
	@Column(name = "ID_METADATO_VALOR")
	private Long id;

	@Column(name = "VALOR", length = 400, nullable = false)
	private String valor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_METADATO", nullable = false)
	private TipoMetaDato metadato;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_EXPEDIENTE", nullable = false)
	private Expediente expediente;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}
	/**
	 * @param valor the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * @return the expediente
	 */
	public Expediente getExpediente() {
		return expediente;
	}
	/**
	 * @param expediente the expediente to set
	 */
	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}

}
