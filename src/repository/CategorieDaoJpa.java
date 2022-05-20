package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import domein.Categorie;
import domein.Mvo;
import domein.Sdg;

public class CategorieDaoJpa extends GenericDaoJpa<Categorie> implements CategorieDao
{

	public CategorieDaoJpa()
	{
		super(Categorie.class);
	}

	@Override
	public List<Categorie> geefAlleCategorieen() throws EntityNotFoundException
	{
        try {
            return em.createNamedQuery("Categorie.alleCategorie�n", Categorie.class)
                .getResultList();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
	}

	@Override
	public List<Sdg> geefSdgVoorCategorie(String catNaam) throws EntityNotFoundException {
		List <Sdg> sdgs = em.createNamedQuery("Sdg.sdgVoorCat", Sdg.class).setParameter("catNaam", catNaam).getResultList();
		return sdgs;
	}

	@Override
	public List<Categorie> geefAlleEchteCategorieen() throws EntityNotFoundException {
        try {
            return em.createNamedQuery("Categorie.alleEchteCategorie�n", Categorie.class)
                .getResultList();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
	}

	@Override
	public int geefCatCountMvo(Mvo m) throws EntityNotFoundException {
        try {
            Query query = em.createNamedQuery("Categorie.geefaantalCatMvo", Categorie.class);
            return Integer.parseInt(query.setParameter("mvo_id", m.getId()).getSingleResult().toString());
         } catch (NoResultException ex) {
             throw new EntityNotFoundException();
         }
	}

}
