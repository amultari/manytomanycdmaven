package it.prova.manytomanycdmaven.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.manytomanycdmaven.dao.EntityManagerUtil;
import it.prova.manytomanycdmaven.dao.genere.GenereDAO;
import it.prova.manytomanycdmaven.model.Cd;
import it.prova.manytomanycdmaven.model.Genere;

public class GenereServiceImpl implements GenereService {

	private GenereDAO genereDAO;

	@Override
	public List<Genere> listAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			genereDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return genereDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Genere caricaSingoloElemento(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			genereDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return genereDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Genere genereInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			genereDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			genereDAO.update(genereInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Genere genereInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			genereDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			genereDAO.insert(genereInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuovi(Genere genereInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			genereDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			genereDAO.delete(genereInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Genere cercaPerDescrizione(String descrizione) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			genereDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return genereDAO.findByDescrizione(descrizione);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public void aggiungiCd(Genere genereInstance, Cd cdInstance) throws Exception {

	}

	@Override
	public void setGenereDAO(GenereDAO genereDAO) {
		this.genereDAO = genereDAO;
	}

}
