
package domein;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import exceptions.InformationRequiredException;
import repository.MvoDao;
import repository.MvoDaoJpa;
import repository.MvoDataDao;
import repository.MvoDataDaoJpa;

public class MvoDataController
{
	
	private MvoDataDao mvoDataRepo;
	private List<MvoData> mvoDatas;
	
	public MvoDataController() {
		setMvoDataRepo(new MvoDataDaoJpa());
		this.mvoDatas = mvoDataRepo.findAll();
	}

	public void setMvoDataRepo(MvoDataDao mvoDataRepo) {
		this.mvoDataRepo = mvoDataRepo;
	}
	
	public List<MvoData> geefMvoDatasVanMvo(String mvo_id){
		return mvoDataRepo.geefAlleMvoDatasVoorMvo(mvo_id);
	}
	
	public Mvo geefMvoMetNaam(String naam) {
		return mvoDataRepo.geefMvoMetNaam(naam);
	}
	
	public List<MvoData> geefMvoDatas()
	{
		return Collections.unmodifiableList(mvoDatas);
	}

	public void voegMvoDataToe(Date date, Mvo mvo, int quarter, int waarde)
			throws InformationRequiredException
	{
		MvoData newMvoData = createMvoData(null, date, mvo, quarter, waarde);
		MvoDaoJpa.startTransaction();
		mvoDataRepo.insert(newMvoData);
		MvoDaoJpa.commitTransaction();
		mvoDatas.add(newMvoData);
	}	
	
	public void update(MvoData mvoData) {
    	MvoDaoJpa.startTransaction();
		mvoDataRepo.update(mvoData);
        MvoDaoJpa.commitTransaction();
	}
	
	public void delete(MvoData mvoData) {
    	MvoDaoJpa.startTransaction();
		mvoDataRepo.delete(mvoData);
        MvoDaoJpa.commitTransaction();
	}

	
	
	public void close()
	{
		MvoDaoJpa.closePersistency();
	}

	private MvoData createMvoData(MvoDataBuilder mb, Date date, Mvo mvo, int quarter, int waarde
			) throws InformationRequiredException
	{
		if (mb == null)
		{
			mb = new MvoDataBuilder();
		}
		mb.buildDatum(date);
		mb.buildId();
		mb.buildMvo(mvo);
		mb.buildQuarter(quarter);
		mb.buildWaarde(waarde);
		return mb.getMvoData();
	}
}
