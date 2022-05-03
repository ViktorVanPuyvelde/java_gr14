package repository;

import javax.persistence.EntityNotFoundException;

import domein.Categorie;

public interface CategorieDao extends GenericDao<Categorie>
{
	public Categorie geefAlleCategorieen() throws EntityNotFoundException;
}
