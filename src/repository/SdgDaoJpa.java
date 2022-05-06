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
	public Sdg geefSdgVoorMvo(int sdgId) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("Mvo.geefSdgVoorMvo", Sdg.class)
            		.setParameter("mvoSdgId", sdgId)
            		.getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
	}
}
