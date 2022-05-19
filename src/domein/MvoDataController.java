
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
	
	public MvoDataController() {
		setMvoDataRepo(new MvoDataDaoJpa());
		
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
	


	public void voegMvoDataToe(Mvo mvo, int waarde,Date date, int quarter)
			throws InformationRequiredException
	{
		MvoData newMvoData = createMvoData(null, mvo,waarde,date, quarter);
		MvoDaoJpa.startTransaction();
		mvoDataRepo.insert(newMvoData);
		MvoDaoJpa.commitTransaction();
		
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

	private MvoData createMvoData(MvoDataBuilder mb, Mvo mvo, int waarde,Date date, int quarter
			) throws InformationRequiredException
	{
		if (mb == null)
		{
			mb = new MvoDataBuilder();
		}
		mb.createNewMvoData();
		mb.buildId();
		mb.buildDatum(date);
		mb.buildMvo(mvo);
		mb.buildQuarter(quarter);
		mb.buildWaarde(waarde);
		return mb.getMvoData();
	}
}
