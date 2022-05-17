package domein;

import java.util.List;

import repository.DatasourceDao;
import repository.DatasourceDaoJpa;

public class DatasourceController
{
	private DatasourceDao datasourceRepo;

	public DatasourceController()
	{
		setDatasourceRepo(new DatasourceDaoJpa());
	}

	public List<Datasource> geefDatasources()
	{
		return datasourceRepo.findAll();
	}

	public void close()
	{
		DatasourceDaoJpa.closePersistency();
	}

	private void setDatasourceRepo(DatasourceDao repo)
	{
		this.datasourceRepo = repo;
	}
	
	public Datasource geefDatasourceMetId(String id) {
		return datasourceRepo.geefDatasourceMetId(id);
	}
}