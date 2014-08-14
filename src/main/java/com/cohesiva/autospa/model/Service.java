package com.cohesiva.autospa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "usluga", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
@XmlRootElement
public class Service implements Serializable {

	private static final long serialVersionUID = 7698670500137299716L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "rodzaj_uslugi")
	private String rodzaj_uslugi;

	@Column
	private Long koszt;

	@ManyToOne
	@JoinColumn(name = "karta_id")
	private Card card;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Slot pozycja;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRodzaj_uslugi() {
		return rodzaj_uslugi;
	}

	public void setRodzaj_uslugi(String rodzaj_uslugi) {
		this.rodzaj_uslugi = rodzaj_uslugi;
	}

	public Long getKoszt() {
		return koszt;
	}

	public void setKoszt(Long koszt) {
		this.koszt = koszt;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Slot getPozycja() {
		return pozycja;
	}

	public void setPozycja(Slot pozycja) {
		this.pozycja = pozycja;
	}

}
