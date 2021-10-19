package it.prova.manytomanycdmaven.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "genere")
public class Genere {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "descrizione")
	private String descrizione;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "generi")
	private Set<Cd> cds = new HashSet<Cd>();
	
	public Genere() {}
	
	public Genere(String descrizione) {
		this.descrizione = descrizione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Set<Cd> getCds() {
		return cds;
	}

	public void setCds(Set<Cd> cds) {
		this.cds = cds;
	}

}
