package repository;

import java.util.List;

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

	@Override
	public List<Sdg> geefSdgsZonderCategorie() throws EntityNotFoundException
	{
		return em.createNamedQuery("Sdg.geefSdgsZonderCategorie", Sdg.class).getResultList();
	}

	@Override
	public void updateCategorieIdSdg(String sdgId, String categorieId) throws EntityNotFoundException
	{
		em.createNamedQuery("Sdg.updateCategorieIdSdg", Sdg.class).setParameter(1, categorieId).setParameter(2, sdgId)
				.executeUpdate();
	}

	@Override
	public String geefCategorieIdVanSdg(String sdgId) throws EntityNotFoundException
	{
		return get(sdgId).getCategorie().getId();
	}
}
