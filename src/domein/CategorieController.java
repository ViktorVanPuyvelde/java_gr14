package domein;

import java.util.List;
import java.util.stream.Collectors;

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

	public List<String> geefAlleEchteCategorienNaam()
	{
		List<Categorie> catList = categorieRepo.geefAlleEchteCategorieen();
		
		return catList.stream().map(Categorie::getName).collect(Collectors.toList());

	}

	public void voegCategorieToe(String name, String iconName, List<String> roles)
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
