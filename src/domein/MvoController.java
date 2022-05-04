package domein;

import java.util.List;

import repository.MvoDao;
import repository.MvoDaoJpa;

public class MvoController
{
	private MvoDao mvoDao;

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

	public void voegMvoToe(String name, String sdg, String info, int goalValue, String datasourceId)
	{
		MvoDaoJpa.startTransaction();
//		"" --> superMvoId
		mvoDao.insert(new Mvo(name, sdg, "", info, goalValue, datasourceId));
		MvoDaoJpa.commitTransaction();
	}

	public void close()
	{
		MvoDaoJpa.closePersistency();
	}
}
