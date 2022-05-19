package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import domein.Mvo;
import domein.MvoData;


public interface MvoDataDao extends GenericDao<MvoData>{
	
	public List<MvoData> geefAlleMvoDatas() throws EntityNotFoundException;
	
	public List<MvoData> geefAlleMvoDatasVoorMvo(String mvo_id) throws EntityNotFoundException;

	public Mvo geefMvoMetNaam(String naam);
	
	
	

}
