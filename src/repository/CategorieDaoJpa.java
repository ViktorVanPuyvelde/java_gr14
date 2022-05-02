package repository;

import javax.persistence.EntityNotFoundException;

import domein.Categorie;

public class CategorieDaoJpa extends GenericDaoJpa<Categorie> implements CategorieDao
{

	public CategorieDaoJpa()
	{
		super(Categorie.class);
	}

	@Override
	public Categorie geefAlleCategorieen() throws EntityNotFoundException
	{
		// TODO Auto-generated method stub
		return null;
	}

}
