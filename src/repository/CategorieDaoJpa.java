package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;

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
	public List<Sdg> geefSdgVoorCategorie(String catNaam) throws EntityNotFoundException {
		List <Sdg> sdgs = em.createNamedQuery("Categorie.sdgVoorCat", Sdg.class).setParameter("catNaam", catNaam).getResultList();
		return sdgs;
	}

}
