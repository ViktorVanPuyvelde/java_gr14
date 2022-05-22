package domein;

import java.util.Collections;
import java.util.List;

import exceptions.InformationRequiredException;
import repository.MvoDao;
import repository.MvoDaoJpa;

public class MvoController
{

	private MvoDao mvoRepo;
	private List<Mvo> mvos;

	public MvoController()
	{
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
		Mvo newMvo = createMvo(null, name, sdg, info, goalValue, datasource, superMvo);
		MvoDaoJpa.startTransaction();
		mvoRepo.insert(newMvo);
		MvoDaoJpa.commitTransaction();
		mvos.add(newMvo);
	}

	public void update(Mvo mvo) throws InformationRequiredException
	{
		MvoBuilder mvoBuilder = new MvoBuilder();
		mvoBuilder.setMvo(mvo);
		Mvo updateMvo = mvoBuilder.getMvo();
		MvoDaoJpa.startTransaction();
		mvoRepo.update(updateMvo);
		MvoDaoJpa.commitTransaction();
	}

	public void delete(Mvo mvo)
	{
		MvoDaoJpa.startTransaction();
		mvoRepo.delete(mvo);
		MvoDaoJpa.commitTransaction();
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
