package it.prova.manytomanycdmaven.dao.cd;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.manytomanycdmaven.model.Cd;

public class CdDAOImpl implements CdDAO {

	private EntityManager entityManager;

	@Override
	public List<Cd> list() throws Exception {
		return entityManager.createQuery("from Cd", Cd.class).getResultList();
	}

	@Override
	public Cd get(Long id) throws Exception {
		return entityManager.find(Cd.class, id);
	}

	@Override
	public void update(Cd input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Cd input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Cd input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Cd findByIdFetchingGeneri(Long id) throws Exception {
		TypedQuery<Cd> query = entityManager
				.createQuery("select c FROM Cd c left join fetch c.generi g where c.id = :idCd", Cd.class);
		query.setParameter("idCd", id);
		return query.getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public List<String> loadListaDescrizioneGeneriAssociateAdUnCd(Long idCdInput) throws Exception {
		TypedQuery<String> query = entityManager
				.createQuery("select g.descrizione FROM Genere g left join g.cds cd where cd.id = :idCd", String.class);
		query.setParameter("idCd", idCdInput);
		return query.getResultList();
	}

}
