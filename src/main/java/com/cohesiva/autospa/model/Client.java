package com.cohesiva.autospa.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "client", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
@XmlRootElement
public class Client implements Serializable {

	private static final long serialVersionUID = 3926585382060466179L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "nazwa_firmy")
	private String nazwaFirmy;

	@Column(name = "adres")
	private String adres;
	
	@Column(name = "imie")
	private String imie;
	
	@Column(name = "nazwisko")
	private String nazwisko;

	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
	private Set<User> users;
	
	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
	private Set<FV> FVs;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazwaFirmy() {
		return nazwaFirmy;
	}

	public void setNazwaFirmy(String nazwaFirmy) {
		this.nazwaFirmy = nazwaFirmy;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<FV> getFVs() {
		return FVs;
	}

	public void setFVs(Set<FV> fVs) {
		FVs = fVs;
	}
}
