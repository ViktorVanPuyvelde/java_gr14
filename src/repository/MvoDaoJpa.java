package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import domein.Categorie;
import domein.Mvo;
import domein.Sdg;

public class MvoDaoJpa extends GenericDaoJpa<Mvo> implements MvoDao{

	public MvoDaoJpa() {
		super(Mvo.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Mvo geefAlleMvos() throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mvo> geefAlleMvosVoorCategorie(String naam) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("Mvo.geefMvosVoorCategorie", Mvo.class)
            		.setParameter("catNaam", naam )
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
