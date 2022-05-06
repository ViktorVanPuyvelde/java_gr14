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
	
	public void close()
	{
		MvoDaoJpa.closePersistency();
	}

}
