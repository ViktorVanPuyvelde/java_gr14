
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
	

	
	
	


	public void voegMvoDataToe(Mvo mvo, String waarde,Date date, int quarter)
			throws InformationRequiredException
	{
		MvoData newMvoData = createMvoData(null, mvo,waarde,date, quarter);
		mvo.getMvo_data().add(newMvoData);
		MvoDataDaoJpa.startTransaction();
		mvoDataRepo.insert(newMvoData);
		MvoDataDaoJpa.commitTransaction();
		
	}	
	
	public void update(MvoData mvoData) {
		MvoDataDaoJpa.startTransaction();
		mvoDataRepo.update(mvoData);
		MvoDataDaoJpa.commitTransaction();
	}
	
	public void delete(MvoData mvoData) {
		MvoDataDaoJpa.startTransaction();
		mvoDataRepo.delete(mvoData);
		MvoDataDaoJpa.commitTransaction();
	}

	
	
	public void close()
	{
		MvoDaoJpa.closePersistency();
	}

	private MvoData createMvoData(MvoDataBuilder mb, Mvo mvo, String waarde,Date date, int quarter
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
