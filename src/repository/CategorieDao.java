package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import domein.Categorie;

public interface CategorieDao extends GenericDao<Categorie>
{
	public List<Categorie> geefAlleCategorieen() throws EntityNotFoundException;
	
	public List<Categorie> geefAlleEchteCategorieen() throws EntityNotFoundException;
}
