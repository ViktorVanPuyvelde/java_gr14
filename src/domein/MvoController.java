package domein;

import java.util.List;

import repository.MvoDao;
import repository.MvoDaoJpa;

public class MvoController
{
	private MvoDao mvoDao;

	protected MvoController()
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

	public void close()
	{
		MvoDaoJpa.closePersistency();
	}
}
