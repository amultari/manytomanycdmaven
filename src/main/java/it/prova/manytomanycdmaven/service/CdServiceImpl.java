package it.prova.manytomanycdmaven.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.manytomanycdmaven.dao.EntityManagerUtil;
import it.prova.manytomanycdmaven.dao.cd.CdDAO;
import it.prova.manytomanycdmaven.model.Cd;
import it.prova.manytomanycdmaven.model.Genere;

public class CdServiceImpl implements CdService {

	private CdDAO cdDAO;

	@Override
	public void setCdDAO(CdDAO cdDAO) {
		this.cdDAO = cdDAO;
	}

	@Override
	public List<Cd> listAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			cdDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return cdDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Cd caricaSingoloElemento(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			cdDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return cdDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Cd caricaSingoloElementoEagerGeneri(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			cdDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return cdDAO.findByIdFetchingGeneri(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Cd cdInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			cdDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			cdDAO.update(cdInstance);

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
	public void inserisciNuovo(Cd cdInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			cdDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			cdDAO.insert(cdInstance);

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
	public void rimuovi(Long idCd) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			cdDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			cdDAO.delete(cdDAO.get(idCd));

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
	public void aggiungiGenere(Cd cdInstance, Genere genereInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			cdDAO.setEntityManager(entityManager);

			// 'attacco' alla sessione di hibernate i due oggetti
			// così jpa capisce che se risulta presente quel cd non deve essere inserito
			cdInstance = entityManager.merge(cdInstance);
			// attenzione che genereInstance deve essere già presente (lo verifica dall'id)
			// se così non è viene lanciata un'eccezione
			genereInstance = entityManager.merge(genereInstance);

			cdInstance.getGeneri().add(genereInstance);
			// l'update non viene richiamato a mano in quanto
			// risulta automatico, infatti il contesto di persistenza
			// rileva che cdInstance ora è dirty vale a dire che una sua
			// proprieta ha subito una modifica (vale anche per i Set ovviamente)
			// inoltre se risultano già collegati lo capisce automaticamente grazie agli id

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
	public void creaECollegaCdEGenere(Cd cdTransientInstance, Genere genereTransientInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			cdDAO.setEntityManager(entityManager);

			// collego le due entità: questa cosa funziona grazie al fatto che ho
			// CascadeType.PERSIST, CascadeType.MERGE dentro l'owner della relazione (Cd in
			// questo caso)
			cdTransientInstance.getGeneri().add(genereTransientInstance);

			// ********************** IMPORTANTE ****************************
			// se io rimuovo i cascade, non funziona più e si deve prima salvare il genere
			// (tramite genereDAO iniettando anch'esso) e poi
			// sfruttare i metodi addTo o removeFrom dentro Cd:
			// GenereDAO genereDAO = MyDaoFactory.getGenereDAOInstance();
			// genereDAO.setEntityManager(entityManager);
			// genereDAO.insert(genereTransientInstance);
			// cdTransientInstance.addToGeneri(genereTransientInstance);
			// in questo caso però se il genere è già presente non ne tiene conto e
			// inserirebbe duplicati, ma è logico
			// ****************************************************************

			// inserisco il cd
			cdDAO.insert(cdTransientInstance);

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
	public List<String> estraiListaDescrizioneGeneriAssociateAdUnCd(Long idCdInput) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			cdDAO.setEntityManager(entityManager);

			return cdDAO.loadListaDescrizioneGeneriAssociateAdUnCd(idCdInput);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

}
