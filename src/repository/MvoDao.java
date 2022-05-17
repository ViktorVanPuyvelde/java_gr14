package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import domein.Datasource;
import domein.Mvo;
import domein.Sdg;

public interface MvoDao extends GenericDao<Mvo>{
	
	public List<Mvo> geefAlleMvos() throws EntityNotFoundException;
	
	public List<Mvo> geefAlleMvosVoorCategorie(String naam) throws EntityNotFoundException;
	
	public Mvo geefMvoMetNaam(String naam) throws EntityNotFoundException;
	
	public void verwijderMvoMetId(String id) throws EntityNotFoundException;
	
	public void updateMvoMetId(String id,String naam,Mvo superMvoId,Sdg sdgId,int doel,Datasource datasourceId,String type) throws EntityNotFoundException;
	

}
