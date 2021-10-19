package it.prova.manytomanycdmaven.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cd")
public class Cd {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "titolo")
	private String titolo;
	@Column(name = "autore")
	private String autore;
	@Column(name = "datapubblicazione")
	private Date dataPubblicazione;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "cd_genere", joinColumns = @JoinColumn(name = "cd_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "genere_id", referencedColumnName = "ID"))
	private Set<Genere> generi = new HashSet<Genere>();
	
	public Cd() {}
	
	public Cd(String titolo, String autore, Date dataPubblicazione) {
		this.titolo = titolo;
		this.autore = autore;
		this.dataPubblicazione = dataPubblicazione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}

	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}

	public Set<Genere> getGeneri() {
		return generi;
	}

	public void setGeneri(Set<Genere> generi) {
		this.generi = generi;
	}

	@Override
	public String toString() {
		String dataPubblicazioneString = dataPubblicazione!=null?new SimpleDateFormat("dd/MM/yyyy").format(dataPubblicazione):" N.D.";
		
		return "Cd [id=" + id + ", titolo=" + titolo + ", autore=" + autore + ", dataPubblicazione=" + dataPubblicazioneString
				+ "]";
	}
	
	//questi due metodi servirebbero nel caso in cui non vi fossero i Cascade in alto (v. creaECollegaCdEGenere di CdServiceImpl)
	public void addToGeneri(Genere genereInstance) {
		this.generi.add(genereInstance);
		genereInstance.getCds().add(this);
	}
	
	public void removeFromGeneri(Genere genereInstance) {
		this.generi.remove(genereInstance);
		genereInstance.getCds().remove(this);
	}

}
