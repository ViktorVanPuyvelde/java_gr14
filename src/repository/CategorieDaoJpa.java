package repository;

import javax.persistence.EntityNotFoundException;

import domein.Categorie;

public class CategorieDaoJpa extends GenericDaoJpa<Categorie> implements CategorieDao
{

	public CategorieDaoJpa()
	{
		super(Categorie.class);
	}

//	@Override
//	public List<Categorie> findAll()
//	{
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <U> Categorie get(U id)
//	{
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Categorie update(Categorie object)
//	{
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void delete(Categorie object)
//	{
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void insert(Categorie object)
//	{
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public <U> boolean exists(U id)
//	{
//		// TODO Auto-generated method stub
//		return false;
//	}

	@Override
	public Categorie geefAlleCategorieen() throws EntityNotFoundException
	{
		// TODO Auto-generated method stub
		return null;
	}

}
