package repository;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import domein.Sdg;

public class SdgDaoJpa extends GenericDaoJpa<Sdg> implements SdgDao
{
	public SdgDaoJpa()
	{
		super(Sdg.class);
	}

	@Override
	public Sdg geefSdgVoorMvo(String sdgId, String mvoId) throws EntityNotFoundException
	{
		try
		{
			System.out.println(sdgId);
			return em.createNamedQuery("Mvo.geefSdgVoorMvo", Sdg.class).setParameter("mvoSdgId", sdgId)
					.setParameter("mvoId", mvoId).getSingleResult();
		} catch (NoResultException ex)
		{
			throw new EntityNotFoundException();
		}
	}

	@Override
	public Sdg geefSdgDoorNaam(String naam) throws EntityNotFoundException
	{
		try
		{
			return em.createNamedQuery("Sdg.geefSdgDoorNaam", Sdg.class).setParameter("naam", naam).getSingleResult();
		} catch (NoResultException e)
		{
			throw new EntityNotFoundException();
		}
	}
}
