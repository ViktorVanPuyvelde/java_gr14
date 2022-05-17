package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import domein.Datasource;
import domein.Mvo;
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
	public void verwijderMvoMetId(String id) throws EntityNotFoundException {
        try {
            Query query = em.createNamedQuery("Mvo.verwijderMvoMetID", Mvo.class)
            		.setParameter("mvoID", id);     
            query.executeUpdate();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
	}

	@Override
	public void updateMvoMetId(String id,String naam,Mvo superMvoId,Sdg sdgId,int doel,Datasource datasourceId,String type) throws EntityNotFoundException {
        try {
            Query query = em.createNamedQuery("Mvo.updateMvoMetID", Mvo.class)
            		.setParameter("mvoName", naam)
            		.setParameter("superMvoId", superMvoId)
            		.setParameter("sdgId", sdgId)
            		.setParameter("doel",doel)
            		.setParameter("datasourceId",datasourceId)
            		.setParameter("type", type)
            		.setParameter("mvoID", id);
            query.executeUpdate();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
		
	}


}
