package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import domein.Mvo;

public interface MvoDao extends GenericDao<Mvo>{
	
	public Mvo geefAlleMvos() throws EntityNotFoundException;
	
	public List<Mvo> geefAlleMvosVoorCategorie(String naam) throws EntityNotFoundException;
	
	public Mvo geefMvoMetNaam(String naam) throws EntityNotFoundException;
	

}
