package domein;

import java.util.Collections;
import java.util.List;

import repository.DatasourceDao;
import repository.DatasourceDaoJpa;

public class DatasourceController
{
	private DatasourceDao datasourceRepo;
	private List<Datasource> datasources;
	
	public DatasourceController()
	{
		setDatasourceRepo(new DatasourceDaoJpa());
		datasources = datasourceRepo.findAll();
	}

	public List<Datasource> geefDatasources()
	{
		return Collections.unmodifiableList(datasources);
	}

	public Datasource geefDatasourceDoorNaam(String naam)
	{
		return datasourceRepo.geefDatasourceDoorNaam(naam);
	}

	public void close()
	{
		DatasourceDaoJpa.closePersistency();
	}

	private void setDatasourceRepo(DatasourceDao repo)
	{
		this.datasourceRepo = repo;
	}
	
	public void voegDatasourceToe(String naam, boolean flag) {
		Datasource d = new Datasource(naam, flag);
		DatasourceDaoJpa.startTransaction();
		this.datasourceRepo.insert(d);
		DatasourceDaoJpa.commitTransaction();
		datasources.add(d);
	}	
	
	public void updateDatasource(Datasource d, String naam, boolean flag) {
		int index = datasources.indexOf(d);
		d.setName(naam);
		d.setFlag(flag);
		DatasourceDaoJpa.startTransaction();
		this.datasourceRepo.update(d);
		DatasourceDaoJpa.commitTransaction();
		datasources.set(index, d);
	}

	public void deleteDatasource(Datasource d) {
		DatasourceDaoJpa.startTransaction();
		this.datasourceRepo.delete(d);
		DatasourceDaoJpa.commitTransaction();
	}	
	public Datasource geefDatasourceMetId(String id) {
		return datasourceRepo.geefDatasourceMetId(id);
	}
}