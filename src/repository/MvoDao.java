package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import domein.Datasource;
import domein.Mvo;
import domein.MvoData;
import domein.Sdg;

public interface MvoDao extends GenericDao<Mvo>{
	
	public List<Mvo> geefAlleMvos() throws EntityNotFoundException;
	
	public List<Mvo> geefAlleMvosVoorCategorie(String naam) throws EntityNotFoundException;
	
	public Mvo geefMvoMetNaam(String naam) throws EntityNotFoundException;

	
	public int geefMvoDatasourceCount(Datasource d) throws EntityNotFoundException;
	

}
