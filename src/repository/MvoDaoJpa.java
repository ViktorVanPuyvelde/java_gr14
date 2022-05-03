package repository;

import domein.Mvo;

public class MvoDaoJpa extends GenericDaoJpa<Mvo> implements MvoDao
{

	public MvoDaoJpa()
	{
		super(Mvo.class);
	}
}
