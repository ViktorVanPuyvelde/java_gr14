package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import domein.Categorie;
import domein.Sdg;

public interface CategorieDao extends GenericDao<Categorie>
{
	public List<Categorie> geefAlleCategorieen() throws EntityNotFoundException;
	public List<Categorie> geefAlleEchteCategorieen() throws EntityNotFoundException;
	public List<Sdg> geefSdgVoorCategorie(String naamCat) throws EntityNotFoundException;	
	
}
