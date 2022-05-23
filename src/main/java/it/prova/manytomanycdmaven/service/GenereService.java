package it.prova.manytomanycdmaven.service;

import java.util.List;

import it.prova.manytomanycdmaven.dao.genere.GenereDAO;
import it.prova.manytomanycdmaven.model.Cd;
import it.prova.manytomanycdmaven.model.Genere;

public interface GenereService {

	public List<Genere> listAll() throws Exception;

	public Genere caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Genere genereInstance) throws Exception;

	public void inserisciNuovo(Genere genereInstance) throws Exception;

	public void rimuovi(Long idGenere) throws Exception;

	public void aggiungiCd(Genere genereInstance, Cd cdInstance) throws Exception;
	
	public Genere cercaPerDescrizione(String descrizione) throws Exception;

	// per injection
	public void setGenereDAO(GenereDAO genereDAO);

}
