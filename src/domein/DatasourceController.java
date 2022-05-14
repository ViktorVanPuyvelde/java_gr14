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
}