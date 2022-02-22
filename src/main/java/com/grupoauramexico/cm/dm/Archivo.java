/**
 * 
 */
package com.grupoauramexico.cm.dm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.BooleanUtils;

/**
 * @author vladimir.aguirre
 *
 */
@Entity
@Table(name = "ARCHIVO")
public class Archivo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4071299237406877064L;
	@Id
	@SequenceGenerator(name = "ARCHIVO_ID_GENERATOR", sequenceName = "SEQ_ARCHIVO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARCHIVO_ID_GENERATOR")
	@Column(name = "ID_ARCHIVO")
	private Long id;
	@Column(name = "NOMBRE", length = 120, nullable = false)
	private String nombre;
	/** Instancia TipoMultimedia para obtener el tipo de la Multimedia. */
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_MULTIMEDIA", nullable = false)
	private TipoMultimedia tipo;
	/**
	 * Representa el archivo.
	 */
	@Lob
	@Column(name = "ARCHIVO", nullable = true)
	private byte[] archivo;

	@Column(name = "PATH", length = 500, nullable = true)
	private String path;

	/**
	 * Punto en el tiempo en que se regitra.
	 */
	@Column(name = "FECHA_REGISTRO", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro;

	@Column(name = "FECHA_MODIFICACION", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaModificacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_EXPEDIENTE", referencedColumnName = "ID_EXPEDIENTE", nullable = false)
	private Expediente expediente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_DOCUMENTO", referencedColumnName = "ID_TIPO_DOCUMENTO", nullable = false)
	private TipoDocumento tipoDocumento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ORIGINAL", nullable = true)
	private Archivo original;

	@OneToMany(mappedBy = "original", fetch = FetchType.LAZY)
	private List<Archivo> versiones;

	@Column(name = "NUMERO_VERSION", nullable = true)
	private Integer numeroVersion;

	@Column(name = "COMENTARIO", nullable = true, columnDefinition = "text")
	private String comentario;

	/**
	 * 
	 */
	@Column(name = "IND_OBSOLETO", nullable = true)
	private Boolean obsoleto = Boolean.FALSE;
	@Column(name = "HASH_MD5", length = 40, nullable = false)
	private String hashMd5;

	/**
	 * Tamaño en KB
	 */
	@Column(name = "SIZE_KB", nullable = false)
	private Float sizeKb;

	/**
	 * Especifica si el estado de la entidad es activo o inactivo.
	 * 
	 * @since 04/05/2021
	 */
	@Column(name = "IND_ACTIVO")
	private Boolean indActivo;
	
	/**
	 * 
	 */
	public Archivo() {
		super();
	}

	/**
	 * @param id
	 * @param fechaRegistro
	 */
	public Archivo(Long id, Date fechaRegistro, Long idOriginal) {
		super();
		this.id = id;
		this.fechaRegistro = fechaRegistro;
		if (idOriginal!=null) {
			this.original = new Archivo(idOriginal);
		}
	}

	/**
	 * 
	 * @param id
	 * @param nombre
	 * @param tipo
	 * @param fechaRegistro
	 * @param fechaModificacion
	 * @param tipoDocumento
	 * @param idOr
	 * @param numeroVersion
	 * @param comentario
	 * @param obsoleto
	 * @param hashMd5
	 * @param sizeKb
	 */
	public Archivo(Long id, String nombre, TipoMultimedia tipo, Date fechaRegistro, Date fechaModificacion,
			TipoDocumento tipoDocumento, Integer numeroVersion, String comentario, Boolean obsoleto,
			String hashMd5, Float sizeKb) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.fechaRegistro = fechaRegistro;
		this.fechaModificacion = fechaModificacion;
		this.tipoDocumento = tipoDocumento;
		this.numeroVersion = numeroVersion;
		this.comentario = comentario;
		this.obsoleto = obsoleto;
		this.hashMd5 = hashMd5;
		this.sizeKb = sizeKb;
	}

	/**
	 * @param id
	 */
	public Archivo(Long id) {
		super();
		this.id = id;
	}

	/**
	 * @param id
	 * @param nombre
	 */
	public Archivo(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Archivo(Long id, String nombre, Date fec) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaRegistro = fec;
	}

	/**
	 * 
	 * @param id
	 * @param nombre
	 * @param fec
	 * @param nomTpo
	 */
	public Archivo(Long id, String nombre, Date fec, Long idTDoc, String nomTpo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaRegistro = fec;
		if (nomTpo != null) {
			tipoDocumento = new TipoDocumento(idTDoc);
			tipoDocumento.setNombre(nomTpo);
		}
	}

	public Archivo(Long id, String nombre, Date fec, Long idTDoc, String nomTpo, Integer orden) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaRegistro = fec;
		if (nomTpo != null) {
			tipoDocumento = new TipoDocumento(idTDoc);
			tipoDocumento.setNombre(nomTpo);
			tipoDocumento.setOrden(orden);
		}
	}

	/**
	 * 
	 * @param id
	 * @param nombre
	 * @param fec
	 * @param idTDoc
	 * @param nomTpo
	 * @param orden
	 * @param version
	 * @param obsoletoP
	 */
	public Archivo(Long id, String nombre, Date fec, Long idTDoc, String nomTpo, Integer orden, Integer version,
			Boolean obsoletoP) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaRegistro = fec;
		if (nomTpo != null) {
			tipoDocumento = new TipoDocumento(idTDoc);
			tipoDocumento.setNombre(nomTpo);
			tipoDocumento.setOrden(orden);
		}
		this.numeroVersion = version;
		this.obsoleto = obsoletoP;
	}

	public Archivo(Long id, String nombre, Date fec, Long idTDoc, String nomTpo, Integer orden, Integer version,
			Boolean obsoletoP, String comment) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaRegistro = fec;
		if (nomTpo != null) {
			tipoDocumento = new TipoDocumento(idTDoc);
			tipoDocumento.setNombre(nomTpo);
			tipoDocumento.setOrden(orden);
		}
		this.numeroVersion = version;
		this.obsoleto = obsoletoP;
		this.comentario = comment;
	}

	/**
	 * 
	 * @param id
	 * @param nombre
	 * @param fec
	 * @param idTDoc
	 * @param nomTpo
	 * @param orden
	 * @param version
	 * @param obsoletoP
	 * @param comment
	 * @param idTipoMM
	 */
	public Archivo(Long id, String nombre, Date fec, Long idTDoc, String nomTpo, Integer orden, Integer version,
			Boolean obsoletoP, String comment, Long idTipoMM) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaRegistro = fec;
		if (nomTpo != null) {
			tipoDocumento = new TipoDocumento(idTDoc);
			tipoDocumento.setNombre(nomTpo);
			tipoDocumento.setOrden(orden);
		}
		this.numeroVersion = version;
		this.obsoleto = obsoletoP;
		this.comentario = comment;
		if (idTipoMM != null) {
			this.tipo = new TipoMultimedia(idTipoMM);
		}
	}

	/**
	 * 
	 * @param id
	 * @param nombre
	 * @param fec
	 * @param idTDoc
	 * @param nomTpo
	 * @param orden
	 * @param version
	 * @param obsoletoP
	 * @param comment
	 * @param idTipoMM
	 * @param indEsc    +
	 */
	public Archivo(Long id, String nombre, Date fec, Long idTDoc, String nomTpo, Integer orden, Integer version,
			Boolean obsoletoP, String comment, Long idTipoMM, Boolean indEsc) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaRegistro = fec;
		if (nomTpo != null) {
			tipoDocumento = new TipoDocumento(idTDoc);
			tipoDocumento.setNombre(nomTpo);
			tipoDocumento.setOrden(orden);
			tipoDocumento.setEscaneo(indEsc);
		}
		this.numeroVersion = version;
		this.obsoleto = obsoletoP;
		this.comentario = comment;
		if (idTipoMM != null) {
			this.tipo = new TipoMultimedia(idTipoMM);
		}
	}

	public Archivo(Long id, String nombre, Date fec, Long idTDoc, String nomTpo, Integer orden, Integer version,
			Boolean obsoletoP, String comment, Long idTipoMM, Boolean indEsc, Boolean indCar) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaRegistro = fec;
		if (nomTpo != null) {
			tipoDocumento = new TipoDocumento(idTDoc);
			tipoDocumento.setNombre(nomTpo);
			tipoDocumento.setOrden(orden);
			tipoDocumento.setEscaneo(indEsc);
			tipoDocumento.setCargable(BooleanUtils.isTrue(indCar));
		}
		this.numeroVersion = version;
		this.obsoleto = obsoletoP;
		this.comentario = comment;
		if (idTipoMM != null) {
			this.tipo = new TipoMultimedia(idTipoMM);
		}
	}
	@Override
	public String toString() {
		final StringBuilder cad = new StringBuilder(getClass().getSimpleName());
		cad.append("=[");
		if (id != null) {
			cad.append("id=").append(id);
		}
		if (nombre != null) {
			cad.append(", nombre=").append(nombre);
		}
		if (sizeKb != null) {
			cad.append(", sizeKb=").append(sizeKb);
		}

		if (hashMd5 != null) {
			cad.append(", hashMd5=").append(hashMd5);
		}
		cad.append("]");
		return cad.toString();
	}
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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the tipo
	 */
	public TipoMultimedia getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TipoMultimedia tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the archivo
	 */
	public byte[] getArchivo() {
		return archivo;
	}

	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	/**
	 * @return the fechaRegistro
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * @param fechaRegistro the fechaRegistro to set
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the evento
	 */
	public Expediente getExpediente() {
		return expediente;
	}

	/**
	 * @param evento the evento to set
	 */
	public void setExpediente(Expediente evento) {
		this.expediente = evento;
	}

	/**
	 * @return the tipoDocumento
	 */
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the original
	 */
	public Archivo getOriginal() {
		return original;
	}

	/**
	 * @param original the original to set
	 */
	public void setOriginal(Archivo original) {
		this.original = original;
	}

	/**
	 * @return the versiones
	 */
	public List<Archivo> getVersiones() {
		return versiones;
	}

	/**
	 * @param versiones the versiones to set
	 */
	public void setVersiones(List<Archivo> versiones) {
		this.versiones = versiones;
	}

	/**
	 * @return the numeroVersion
	 */
	public Integer getNumeroVersion() {
		return numeroVersion;
	}

	/**
	 * @param numeroVersion the numeroVersion to set
	 */
	public void setNumeroVersion(Integer numeroVersion) {
		this.numeroVersion = numeroVersion;
	}

	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * @return the obsoleto
	 */
	public Boolean getObsoleto() {
		return obsoleto;
	}

	/**
	 * @param obsoleto the obsoleto to set
	 */
	public void setObsoleto(Boolean obsoleto) {
		this.obsoleto = obsoleto;
	}

	/**
	 * @return the fechaModificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * @return the hashMd5
	 */
	public String getHashMd5() {
		return hashMd5;
	}

	/**
	 * @param hashMd5 the hashMd5 to set
	 */
	public void setHashMd5(String hashMd5) {
		this.hashMd5 = hashMd5;
	}

	/**
	 * @return the sizeKb
	 */
	public Float getSizeKb() {
		return sizeKb;
	}

	/**
	 * @param sizeKb the sizeKb to set
	 */
	public void setSizeKb(Float sizeKb) {
		this.sizeKb = sizeKb;
	}

	/**
	 * @return the indActivo
	 */
	public Boolean getIndActivo() {
		return indActivo;
	}

	/**
	 * @param indActivo the indActivo to set
	 */
	public void setIndActivo(Boolean indActivo) {
		this.indActivo = indActivo;
	}

}
