package repository;

import javax.persistence.EntityNotFoundException;

import domein.Sdg;

public interface SdgDao extends GenericDao<Sdg>
{
	
	public Sdg geefSdgVoorMvo(int sdgId) throws EntityNotFoundException;

}
