package domein;

import java.util.List;

import exceptions.InformationRequiredException;
import repository.MvoDao;
import repository.MvoDaoJpa;

public class MvoController
{

	private MvoDao MvoRepo;

	public MvoController()
	{
		setMvoRepo(new MvoDaoJpa());
	}

	public void setMvoRepo(MvoDao mvoRepo)
	{
		MvoRepo = mvoRepo;
	}

	public List<Mvo> geefMvosVanCategorie(String categorie)
	{
		return MvoRepo.geefAlleMvosVoorCategorie(categorie);
	}

	public Mvo geefMvoMetNaam(String naam)
	{
		return MvoRepo.geefMvoMetNaam(naam);
	}

	public void setMvoDao(MvoDao MvoRepo)
	{
		this.MvoRepo = MvoRepo;
	}

	public List<Mvo> geefMvos()
	{
		return MvoRepo.findAll();
	}

	public void voegMvoToe(String name, Sdg sdg, List<String> info, int goalValue, Datasource datasource, Mvo superMvo)
			throws InformationRequiredException
	{
		Mvo newMvo = createMvo(null, name, sdg, info, goalValue, datasource, superMvo);
		MvoDaoJpa.startTransaction();
		MvoRepo.insert(newMvo);
		MvoDaoJpa.commitTransaction();
	}

	public void close()
	{
		MvoDaoJpa.closePersistency();
	}

	public Mvo createMvo(MvoBuilder mb, String name, Sdg sdg, List<String> info, int goalValue, Datasource datasource,
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
