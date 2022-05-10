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
	}

	public void setSdgRepo(SdgDao sdgRepo)
	{
		this.sdgRepo = sdgRepo;
	}

	public List<Sdg> geefSdgs()
	{
		return sdgRepo.findAll();
	}
	
	public Sdg geefSdgVoorMvo(String id,String mvoId) {
		return sdgRepo.geefSdgVoorMvo(id,mvoId);
	}

	public void close()
	{
		SdgDaoJpa.closePersistency();
	}
}
