package domein;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collections;
import java.util.List;

import exceptions.InformationRequiredException;
import repository.MvoDao;
import repository.MvoDaoJpa;

public class MvoController
{

	private MvoDao mvoRepo;
	private List<Mvo> mvos;
    private PropertyChangeSupport subject;
    private int createOrUpdateOrDelete = 0;
	
	public MvoController()
	{
		subject = new PropertyChangeSupport(this);
		setMvoRepo(new MvoDaoJpa());
		this.mvos = mvoRepo.findAll();
	}

	public void setMvoRepo(MvoDao mvoRepo)
	{
		this.mvoRepo = mvoRepo;
	}

	public List<Mvo> geefMvosVanCategorie(String categorie)
	{
		return mvoRepo.geefAlleMvosVoorCategorie(categorie);
	}

	public Mvo geefMvoMetNaam(String naam)
	{
		return mvoRepo.geefMvoMetNaam(naam);
	}
	
	public int geefCountMVODatasource(Datasource d) {
		return mvoRepo.geefMvoDatasourceCount(d);
	}
	
	public List<Mvo> geefMvos()
	{
		return Collections.unmodifiableList(mvos);
	}

	public void voegMvoToe(String name, Sdg sdg, List<String> info, int goalValue, Datasource datasource, Mvo superMvo)
			throws InformationRequiredException
	{
		createOrUpdateOrDelete = 1;
		Mvo newMvo = createMvo(null, name, sdg, info, goalValue, datasource, superMvo);
		MvoDaoJpa.startTransaction();
		mvoRepo.insert(newMvo);
		MvoDaoJpa.commitTransaction();
		mvos.add(newMvo);
        subject.firePropertyChange("createOrUpdateOrDelete", 0, createOrUpdateOrDelete);
	}

	public void update(Mvo mvo) throws InformationRequiredException
	{
		int index = mvos.indexOf(mvo);
		createOrUpdateOrDelete = 2;
		MvoBuilder mvoBuilder = new MvoBuilder();
		mvoBuilder.setMvo(mvo);
		Mvo updateMvo = mvoBuilder.getMvo();
		MvoDaoJpa.startTransaction();
		mvoRepo.update(updateMvo);
		MvoDaoJpa.commitTransaction();
		mvos.set(index, updateMvo);
        subject.firePropertyChange("createOrUpdateOrDelete", 0, createOrUpdateOrDelete);
	}

	public void delete(Mvo mvo)
	{
		createOrUpdateOrDelete = 3;
		MvoDaoJpa.startTransaction();
		mvoRepo.delete(mvo);
		MvoDaoJpa.commitTransaction();
		mvos.remove(mvo);
        subject.firePropertyChange("createOrUpdateOrDelete", 0, createOrUpdateOrDelete);
	}

	//nieuwe luisteraar inschrijven
    public void addPropertyChangeListener(PropertyChangeListener pcl) { 
        subject.addPropertyChangeListener(pcl);
        pcl.propertyChange(
        		new PropertyChangeEvent(pcl, "createOrUpdateOrDelete", createOrUpdateOrDelete, 0));
    }

	public void removePropertyChangeListener(PropertyChangeListener pcl)
	{
		subject.removePropertyChangeListener(pcl);
	}
	
	public void close()
	{
		MvoDaoJpa.closePersistency();
	}

	private Mvo createMvo(MvoBuilder mb, String name, Sdg sdg, List<String> info, int goalValue, Datasource datasource,
			Mvo superMvo) throws InformationRequiredException
	{
		if (mb == null)
		{
			mb = new MvoBuilder();
		}
		mb.createNewMvo();
		mb.buildId();
		mb.buildName(name);
		mb.buildSdg(sdg);
		mb.buildInfo(info);
		mb.buildGoalValue(goalValue);
		mb.buildDatasource(datasource);
		mb.buildSuperMvo(superMvo);
		return mb.getMvo();
	}
}
