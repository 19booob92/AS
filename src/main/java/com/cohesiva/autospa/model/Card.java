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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "card", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
@XmlRootElement
public class Card implements Serializable {

	private static final long serialVersionUID = 6913506585597992474L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "aktywna")
	private boolean aktywna;

	@ManyToOne
	@JoinColumn(name = "uzytkownik_id")
	private User user;
	
	@OneToMany(mappedBy = "card", fetch = FetchType.EAGER)
	private Set<Service> services;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isAktywna() {
		return aktywna;
	}

	public void setAktywna(boolean aktywna) {
		this.aktywna = aktywna;
	}

	public User getUzytkownik() {
		return user;
	}

	public void setUzytkownik(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Service> getServices() {
		return services;
	}

	public void setServices(Set<Service> services) {
		this.services = services;
	}

}
