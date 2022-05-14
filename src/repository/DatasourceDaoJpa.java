package repository;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import domein.Datasource;

public class DatasourceDaoJpa extends GenericDaoJpa<Datasource> implements DatasourceDao
{

	public DatasourceDaoJpa()
	{
		super(Datasource.class);
	}

	@Override
	public Datasource geefDatasourceDoorNaam(String naam)
	{
		try
		{
			return em.createNamedQuery("Datasource.geefDatasourceDoorNaam", Datasource.class).setParameter("naam", naam)
					.getSingleResult();
		} catch (NoResultException e)
		{
			throw new EntityNotFoundException();
		}
	}
}
