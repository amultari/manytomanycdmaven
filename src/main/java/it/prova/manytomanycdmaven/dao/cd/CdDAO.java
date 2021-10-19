package it.prova.manytomanycdmaven.dao.cd;

import it.prova.manytomanycdmaven.dao.IBaseDAO;
import it.prova.manytomanycdmaven.model.Cd;

public interface CdDAO extends IBaseDAO<Cd>{
	
	public Cd findByIdFetchingGeneri(Long id) throws Exception;

}
