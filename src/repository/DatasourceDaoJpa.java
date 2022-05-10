package repository;

import domein.Datasource;

public class DatasourceDaoJpa extends GenericDaoJpa<Datasource> implements DatasourceDao
{

	public DatasourceDaoJpa()
	{
		super(Datasource.class);
	}

//	@Override
//	public List<Datasource> findAll() {
//		return em.createNamedQuery("Datasource.alleDatasources").getResultList();
//	}

}