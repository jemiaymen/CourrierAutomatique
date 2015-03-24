package com.model;

// Generated 23 mars 2015 12:42:15 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Pochette generated by hbm2java
 */
@Entity
@Table(name = "pochette", catalog = "courrier")
public class Pochette implements java.io.Serializable {

	private Integer id;
	private int nbr;
	private Set<Courrier> courriers = new HashSet<Courrier>(0);

	public Pochette() {
	}

	public Pochette(int nbr) {
		this.nbr = nbr;
	}

	public Pochette(int nbr, Set<Courrier> courriers) {
		this.nbr = nbr;
		this.courriers = courriers;
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

	@Column(name = "nbr", nullable = false)
	public int getNbr() {
		return this.nbr;
	}

	public void setNbr(int nbr) {
		this.nbr = nbr;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pochette")
	public Set<Courrier> getCourriers() {
		return this.courriers;
	}

	public void setCourriers(Set<Courrier> courriers) {
		this.courriers = courriers;
	}

}