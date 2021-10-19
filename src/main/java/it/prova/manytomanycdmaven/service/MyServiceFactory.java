package it.prova.manytomanycdmaven.service;

import it.prova.manytomanycdmaven.dao.MyDaoFactory;

public class MyServiceFactory {

	private static CdService cdServiceInstance = null;
	private static GenereService genereServiceInstance = null;

	public static CdService getCdServiceInstance() {
		if (cdServiceInstance == null)
			cdServiceInstance = new CdServiceImpl();

		cdServiceInstance.setCdDAO(MyDaoFactory.getCdDAOInstance());

		return cdServiceInstance;
	}

	public static GenereService getGenereServiceInstance() {
		if (genereServiceInstance == null)
			genereServiceInstance = new GenereServiceImpl();

		genereServiceInstance.setGenereDAO(MyDaoFactory.getGenereDAOInstance());

		return genereServiceInstance;
	}

}
