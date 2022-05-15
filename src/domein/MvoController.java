package domein;

import java.util.Collections;
import java.util.List;

import repository.MvoDao;
import repository.MvoDaoJpa;

public class MvoController
{
	
	private MvoDao mvoRepo;
	private List<Mvo> mvos;
	
	public MvoController() {
		setMvoRepo(new MvoDaoJpa());
		this.mvos = mvoRepo.findAll();
	}

	public void setMvoRepo(MvoDao mvoRepo) {
		this.mvoRepo = mvoRepo;
	}
	
	public List<Mvo> geefMvosVanCategorie(String categorie){
		return mvoRepo.geefAlleMvosVoorCategorie(categorie);
	}
	
	public Mvo geefMvoMetNaam(String naam) {
		return mvoRepo.geefMvoMetNaam(naam);
	}
	
	public List<Mvo> geefMvos()
	{
		return Collections.unmodifiableList(mvos);
	}
	
	public void voegMvoToe(String name, Sdg sdg, List<String> info, int goalValue, Datasource datasource, Mvo superMvo)
	{
		Mvo m = new Mvo(name, sdg, info, goalValue, datasource, superMvo);
		MvoDaoJpa.startTransaction();
		mvoRepo.insert(m);
		MvoDaoJpa.commitTransaction();
		mvos.add(m);
	}
	
	public void close()
	{
		MvoDaoJpa.closePersistency();
	}
}
