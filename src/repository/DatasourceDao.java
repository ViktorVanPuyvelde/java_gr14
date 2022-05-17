package repository;

import javax.persistence.EntityNotFoundException;

import domein.Datasource;

public interface DatasourceDao extends GenericDao<Datasource> {
	
	public Datasource geefDatasourceMetId(String id) throws EntityNotFoundException;

}
