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
	
	public void voegDatasourceToe(String naam, boolean flag) {
		DatasourceDaoJpa.startTransaction();
		this.datasourceRepo.insert(new Datasource(naam, flag));
		DatasourceDaoJpa.commitTransaction();
	}	
	
	public void updateDatasource(Datasource d, String naam, boolean flag) {
		d.setName(naam);
		d.setFlag(flag);
		DatasourceDaoJpa.startTransaction();
		this.datasourceRepo.update(d);
		DatasourceDaoJpa.commitTransaction();
	}
	
	public void delete(Datasource d) {
		DatasourceDaoJpa.startTransaction();
		this.datasourceRepo.delete(d);
		DatasourceDaoJpa.commitTransaction();
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