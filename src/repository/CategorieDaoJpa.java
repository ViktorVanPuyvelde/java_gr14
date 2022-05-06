package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import domein.Categorie;

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
            return em.createNamedQuery("Categorie.alleCategoriën", Categorie.class)
                .getResultList();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
	}

	@Override
	public List<Categorie> geefAlleEchteCategorieen() throws EntityNotFoundException {
        try {
            return em.createNamedQuery("Categorie.alleEchteCategoriën", Categorie.class)
                .getResultList();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
	}

}
