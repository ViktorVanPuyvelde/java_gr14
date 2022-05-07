package domein;

import java.util.List;

import repository.DatasourceDao;
import repository.DatasourceDaoJpa;

public class DatasourceController
{
	private DatasourceBeheerder cb = new DatasourceBeheerder();
	private DatasourceDao datasourceRepo;
	
	public DatasourceController() {
		setDatasourceRepo(new DatasourceDaoJpa());
	}

	public List<Datasource> geefDatasources()
	{
		return datasourceRepo.findAll();
	}

	public void close()
	{
		cb.closePersistentie();
	}
	
	private void setDatasourceRepo(DatasourceDao repo) {
		this.datasourceRepo = repo;
	}
}