package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import domein.Mvo;
import domein.MvoData;


public interface MvoDataDao extends GenericDao<MvoData>{

	List<MvoData> geefAlleMvoDatas() throws EntityNotFoundException;
	
	

}
