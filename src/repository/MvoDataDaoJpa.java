package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import domein.Mvo;
import domein.MvoData;


public class MvoDataDaoJpa extends GenericDaoJpa<MvoData> implements MvoDataDao{

	public MvoDataDaoJpa() {
		super(MvoData.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<MvoData> geefAlleMvoDatas() throws EntityNotFoundException {
		return super.findAll();
	}

	@Override
	public List<MvoData> geefAlleMvoDatasVoorMvo(String mvo_id) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("MvoData.geefMvoDatasVoorMvo", MvoData.class)
            		.setParameter("mvo_id", mvo_id )
            		.getResultList();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
	}

	@Override
	public Mvo geefMvoMetNaam(String naam) throws EntityNotFoundException {
	        try {
	            return em.createNamedQuery("Mvo.geefMvoMetNaam", Mvo.class)
	            		.setParameter("mvoNaam", naam )
	            		.getSingleResult();
	        } catch (NoResultException ex) {
	            throw new EntityNotFoundException();
	        } 
	}

	

}
