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
	public Datasource geefDatasourceMetId(String id) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("Datasource.geefDatasourceMetId", Datasource.class)
            		.setParameter("datasource_id", id )
            		.getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
	}

//	@Override
//	public List<Datasource> findAll() {
//		return em.createNamedQuery("Datasource.alleDatasources").getResultList();
//	}

}
