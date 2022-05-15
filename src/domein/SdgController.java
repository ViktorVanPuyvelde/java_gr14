package domein;

import java.util.Collections;
import java.util.List;

import repository.SdgDao;
import repository.SdgDaoJpa;

public class SdgController
{
	private SdgDao sdgRepo;
	private List<Sdg> sdgs;
	
	public SdgController()
	{
		setSdgRepo(new SdgDaoJpa());
		sdgs = sdgRepo.findAll();
	}

	public void setSdgRepo(SdgDao sdgRepo)
	{
		this.sdgRepo = sdgRepo;
	}

	public List<Sdg> geefSdgs()
	{
		return Collections.unmodifiableList(sdgs);
	}
	
	public Sdg geefSdgVoorMvo(String id,String mvoId) {
		return sdgRepo.geefSdgVoorMvo(id,mvoId);
	}

	public Sdg geefSdg(String id)
	{
		return sdgRepo.get(id);
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
