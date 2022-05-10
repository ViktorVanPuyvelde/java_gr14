package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import domein.Categorie;
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
		return super.findAll();
	}

	@Override
	public List<Sdg> geefSdgVoorCategorie(String naamCat) throws EntityNotFoundException {
		List <Sdg> sdgs = em.createNamedQuery("Categorie.sdgVoorCat", Sdg.class).setParameter("catNaam", naamCat).getResultList();
		return sdgs;
        try {
            return em.createNamedQuery("Categorie.alleCategori�n", Categorie.class)
                .getResultList();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
	}

	@Override
	public List<Categorie> geefAlleEchteCategorieen() throws EntityNotFoundException {
        try {
            return em.createNamedQuery("Categorie.alleEchteCategori�n", Categorie.class)
                .getResultList();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
	}

}
