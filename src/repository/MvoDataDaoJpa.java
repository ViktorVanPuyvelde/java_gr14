package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import domein.Mvo;
import domein.MvoData;


public class MvoDataDaoJpa extends GenericDaoJpa<MvoData> implements MvoDataDao{

	public MvoDataDaoJpa() {
		super(MvoData.class);
	
	}

	
	  @Override 
	  public List<MvoData> geefAlleMvoDatas() throws
	  EntityNotFoundException { return super.findAll(); }
	 



	

	

}
