package it.prova.manytomanycdmaven.service;

import java.util.List;

import it.prova.manytomanycdmaven.dao.cd.CdDAO;
import it.prova.manytomanycdmaven.model.Cd;
import it.prova.manytomanycdmaven.model.Genere;

public interface CdService {

	public List<Cd> listAll() throws Exception;

	public Cd caricaSingoloElemento(Long id) throws Exception;
	
	public Cd caricaSingoloElementoEagerGeneri(Long id) throws Exception;

	public void aggiorna(Cd cdInstance) throws Exception;

	public void inserisciNuovo(Cd cdInstance) throws Exception;

	public void rimuovi(Long idCd) throws Exception;

	public void aggiungiGenere(Cd cdInstance, Genere genereInstance) throws Exception;
	
	public void creaECollegaCdEGenere(Cd cdTransientInstance, Genere genereTransientInstance) throws Exception;
	
	public List<String> estraiListaDescrizioneGeneriAssociateAdUnCd(Long idCdInput) throws Exception;

	// per injection
	public void setCdDAO(CdDAO cdDAO);

}
