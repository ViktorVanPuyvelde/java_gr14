package domein;

import java.util.List;

import repository.SdgDao;
import repository.SdgDaoJpa;

public class SdgController
{
	private SdgDao sdgRepo;

	public SdgController()
	{
		setSdgRepo(new SdgDaoJpa());
		//populateDB();
	}

	public void setSdgRepo(SdgDao sdgRepo)
	{
		this.sdgRepo = sdgRepo;
	}

	public List<Sdg> geefSdgs()
	{
		return sdgRepo.findAll();
	}

	public void close()
	{
		SdgDaoJpa.closePersistency();
	}
	
//	private void populateDB() {
//		sdgRepo.insert(new Sdg("Geen Armoede", "img1"	));
//		sdgRepo.insert(new Sdg("Geen Honger", "img2"));
//		sdgRepo.insert(new Sdg("Goede Gezondheid", "img3"));
//		sdgRepo.insert(new Sdg("Kwaliteits Onderwijs", "img4"));
//		sdgRepo.insert(new Sdg("Gender Gelijkheid", "img5"));
//	}
}
