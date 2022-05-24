package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import domein.Datasource;
import domein.Mvo;
import domein.MvoData;
import domein.Sdg;

public class MvoDaoJpa extends GenericDaoJpa<Mvo> implements MvoDao{

	public MvoDaoJpa() {
		super(Mvo.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Mvo> geefAlleMvos() throws EntityNotFoundException {
		return super.findAll();
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


	@Override
	public int geefMvoDatasourceCount(Datasource d) throws EntityNotFoundException {
        try {
           Query query = em.createNamedQuery("Mvo.geefaantalMVOsDatasource", Mvo.class);
           return Integer.parseInt(query.setParameter("datasource_id", d.getId()).getSingleResult().toString());
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
	}

}
