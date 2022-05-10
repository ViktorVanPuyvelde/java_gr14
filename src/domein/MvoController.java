package domein;

import java.util.List;

import repository.MvoDao;
import repository.MvoDaoJpa;

public class MvoController
{
	
	private MvoDao MvoRepo;
	
	public MvoController() {
		setMvoRepo(new MvoDaoJpa());
	}


	public void setMvoRepo(MvoDao mvoRepo) {
		MvoRepo = mvoRepo;
	}
	
	public List<Mvo> geefMvosVanCategorie(String categorie){
		return MvoRepo.geefAlleMvosVoorCategorie(categorie);
	}
	
	public Mvo geefMvoMetNaam(String naam) {
		return MvoRepo.geefMvoMetNaam(naam);
	}
	
	public List<Mvo> geefMvos()
	{
		return MvoRepo.findAll();
	}
	
	public void voegMvoToe(String name, Sdg sdg, List<String> info, int goalValue, Datasource datasource, Mvo superMvo)
	{
		MvoDaoJpa.startTransaction();
		//MvoRepo.insert(new Mvo(name, sdg, info, goalValue, datasource, superMvo));
		MvoDaoJpa.commitTransaction();
	}
	
	public void close()
	{
		MvoDaoJpa.closePersistency();
	}

	public MvoController()
	{
		setMvoDao(new MvoDaoJpa());
	}

	public void setMvoDao(MvoDao mvoDao)
	{
		this.mvoDao = mvoDao;
	}

	public List<Mvo> geefMvos()
	{
		return mvoDao.findAll();
	}

	public void voegMvoToe(String name, Sdg sdg, List<String> info, int goalValue, Datasource datasource, Mvo superMvo)
	{
		MvoDaoJpa.startTransaction();
		mvoDao.insert(new Mvo(name, sdg, info, goalValue, datasource, superMvo));
		MvoDaoJpa.commitTransaction();
	}

	public void close()
	{
		MvoDaoJpa.closePersistency();
	}
}
