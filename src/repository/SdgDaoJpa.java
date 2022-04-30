package repository;

import domein.Sdg;

public class SdgDaoJpa extends GenericDaoJpa<Sdg> implements SdgDao
{
	public SdgDaoJpa()
	{
		super(Sdg.class);
	}
}
