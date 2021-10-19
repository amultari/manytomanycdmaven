package it.prova.manytomanycdmaven.dao.genere;

import it.prova.manytomanycdmaven.dao.IBaseDAO;
import it.prova.manytomanycdmaven.model.Genere;

public interface GenereDAO extends IBaseDAO<Genere>{
	
	public Genere findByDescrizione(String descrizioneInput) throws Exception;

}
