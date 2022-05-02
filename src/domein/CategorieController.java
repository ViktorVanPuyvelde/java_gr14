package domein;

import java.util.List;

import repository.CategorieDao;
import repository.CategorieDaoJpa;

public class CategorieController
{
	private CategorieDao categorieRepo;

	public CategorieController()
	{
		setCategorieRepo(new CategorieDaoJpa());
	}

	public void setCategorieRepo(CategorieDao categorieRepo)
	{
		this.categorieRepo = categorieRepo;
	}

	public List<Categorie> geefCategorien()
	{
		return categorieRepo.findAll();

	}

	public void voegCategorieToe(String name, String iconName, String[] roles)
	{
		CategorieDaoJpa.startTransaction();
		categorieRepo.insert(new Categorie(name, iconName, roles, true));
		CategorieDaoJpa.commitTransaction();
	}

	public void close()
	{
		CategorieDaoJpa.closePersistency();
	}
}
