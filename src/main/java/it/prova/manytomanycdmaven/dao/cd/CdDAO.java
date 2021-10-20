package it.prova.manytomanycdmaven.dao.cd;

import java.util.List;

import it.prova.manytomanycdmaven.dao.IBaseDAO;
import it.prova.manytomanycdmaven.model.Cd;

public interface CdDAO extends IBaseDAO<Cd>{
	
	public Cd findByIdFetchingGeneri(Long id) throws Exception;
	public List<String> loadListaDescrizioneGeneriAssociateAdUnCd(Long idCdInput) throws Exception;

}
