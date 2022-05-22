package domein;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collections;
import java.util.List;

import exceptions.InformationRequiredException;
import repository.DatasourceDao;
import repository.DatasourceDaoJpa;

public class DatasourceController
{
	private DatasourceDao datasourceRepo;
	private List<Datasource> datasources;
    private PropertyChangeSupport subject;
    private int createOrUpdateOrDelete = 0;

	public DatasourceController()
	{
		subject = new PropertyChangeSupport(this);
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
	
	public void voegDatasourceToe(String naam, boolean flag) throws InformationRequiredException {
		createOrUpdateOrDelete = 1;
		Datasource d = createOrUpdateDatasource(null, naam, flag);
		DatasourceDaoJpa.startTransaction();
		this.datasourceRepo.insert(d);
		DatasourceDaoJpa.commitTransaction();
		datasources.add(d);
        subject.firePropertyChange("createOrUpdateOrDelete", 0, createOrUpdateOrDelete);
	}	
	
	private Datasource createOrUpdateDatasource(DatasourceBuilder builder, String naam, boolean flag) throws InformationRequiredException {
		if (builder == null) { 
			builder = new DatasourceBuilder();
			builder.createNewDatasource();
			builder.buildId();
		}
		
		builder.buildName(naam);
		builder.buildFlag(flag);
		
		return builder.getDatasource();
	}

	public void updateDatasource(Datasource d, String naam, boolean flag) throws InformationRequiredException {
		createOrUpdateOrDelete = 2;
		int index = datasources.indexOf(d);
		DatasourceBuilder builder = new DatasourceBuilder();
		builder.setDatasource(d);
		Datasource datasource = createOrUpdateDatasource(builder, naam, flag);
		DatasourceDaoJpa.startTransaction();
		this.datasourceRepo.update(datasource);
		DatasourceDaoJpa.commitTransaction();
		datasources.set(index, d);
        subject.firePropertyChange("createOrUpdateOrDelete", 0, createOrUpdateOrDelete);
	}
	
	public void delete(Datasource d) {
		DatasourceDaoJpa.startTransaction();
		this.datasourceRepo.delete(d);
		DatasourceDaoJpa.commitTransaction();
	}

	public void deleteDatasource(Datasource d) {
		createOrUpdateOrDelete = 3;
		DatasourceDaoJpa.startTransaction();
		this.datasourceRepo.delete(d);
		DatasourceDaoJpa.commitTransaction();
		datasources.remove(d);
        subject.firePropertyChange("createOrUpdateOrDelete", 0, createOrUpdateOrDelete);

	}	
	public Datasource geefDatasourceMetId(String id) {
		return datasourceRepo.geefDatasourceMetId(id);
	}
	
    public void addPropertyChangeListener(PropertyChangeListener pcl) { 
        subject.addPropertyChangeListener(pcl);
        pcl.propertyChange(
        		new PropertyChangeEvent(pcl, "createOrUpdateOrDelete", createOrUpdateOrDelete, 0));
    }

	public void removePropertyChangeListener(PropertyChangeListener pcl)
	{
		subject.removePropertyChangeListener(pcl);
	}

	public Datasource getDatasource(String naam) {
		return datasources.stream().filter((d) -> d.getName().equals(naam)).findAny().get();
	}

}