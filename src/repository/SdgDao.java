package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import domein.Sdg;

public interface SdgDao extends GenericDao<Sdg>
{

	public Sdg geefSdgVoorMvo(String sdgId, String mvoId) throws EntityNotFoundException;

	public Sdg geefSdgDoorNaam(String naam) throws EntityNotFoundException;

	public List<Sdg> geefSdgsZonderCategorie() throws EntityNotFoundException;

}
