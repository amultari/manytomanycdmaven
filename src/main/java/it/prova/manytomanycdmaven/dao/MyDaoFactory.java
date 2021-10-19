package it.prova.manytomanycdmaven.dao;

import it.prova.manytomanycdmaven.dao.cd.CdDAO;
import it.prova.manytomanycdmaven.dao.cd.CdDAOImpl;
import it.prova.manytomanycdmaven.dao.genere.GenereDAO;
import it.prova.manytomanycdmaven.dao.genere.GenereDAOImpl;

public class MyDaoFactory {

	private static CdDAO cdDaoInstance = null;
	private static GenereDAO genereDaoInstance = null;

	public static CdDAO getCdDAOInstance() {
		if (cdDaoInstance == null)
			cdDaoInstance = new CdDAOImpl();

		return cdDaoInstance;
	}

	public static GenereDAO getGenereDAOInstance() {
		if (genereDaoInstance == null)
			genereDaoInstance = new GenereDAOImpl();

		return genereDaoInstance;
	}

}
