package com.model;

// Generated 23 mars 2015 12:42:15 by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Mode generated by hbm2java
 */
@Entity
@Table(name = "mode", catalog = "courrier")
public class Mode implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Courrier courrier;
	private String type;
	private int nbrrecom;
	private String transporteur;
	private String chauffeur;
	private Date daterec;

	public Mode() {
	}

	public Mode(Courrier courrier, String type, int nbrrecom,
			String transporteur, String chauffeur, Date daterec) {
		this.courrier = courrier;
		this.type = type;
		this.nbrrecom = nbrrecom;
		this.transporteur = transporteur;
		this.chauffeur = chauffeur;
		this.daterec = daterec;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cid", nullable = false)
	public Courrier getCourrier() {
		return this.courrier;
	}

	public void setCourrier(Courrier courrier) {
		this.courrier = courrier;
	}

	@Column(name = "type", nullable = false, length = 250)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "nbrrecom", nullable = false)
	public int getNbrrecom() {
		return this.nbrrecom;
	}

	public void setNbrrecom(int nbrrecom) {
		this.nbrrecom = nbrrecom;
	}

	@Column(name = "transporteur", nullable = false, length = 250)
	public String getTransporteur() {
		return this.transporteur;
	}

	public void setTransporteur(String transporteur) {
		this.transporteur = transporteur;
	}

	@Column(name = "chauffeur", nullable = false, length = 250)
	public String getChauffeur() {
		return this.chauffeur;
	}

	public void setChauffeur(String chauffeur) {
		this.chauffeur = chauffeur;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "daterec", nullable = false, length = 0)
	public Date getDaterec() {
		return this.daterec;
	}

	public void setDaterec(Date daterec) {
		this.daterec = daterec;
	}

}
