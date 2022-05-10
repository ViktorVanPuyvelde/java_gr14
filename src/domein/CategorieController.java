package domein;

import java.util.List;
import java.util.stream.Collectors;

import repository.CategorieDao;
import repository.CategorieDaoJpa;

public class CategorieController
{
	private CategorieDao categorieRepo;
	private List<Categorie> allCat;
	
	public CategorieController()
	{
		setCategorieRepo(new CategorieDaoJpa());
		//populateDB();
		allCat = geefCategorien();
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

	public Categorie raadpleegCategorie(Categorie c) {
		List<Sdg> sdgs = categorieRepo.geefSdgVoorCategorie(c.getName());
		c.setSdgs(sdgs);
		return c;
	}
	
//	private void populateDB() {
//		categorieRepo.insert(new Categorie("Profit", "icon1", new String[] {"manager", "stakeholder", "coördinator"}, true));
//		categorieRepo.insert(new Categorie("Planet", "icon2", new String[] {"manager", "stakeholder", "coördinator"}, true));
//		categorieRepo.insert(new Categorie("People", "icon3", new String[] {"manager", "stakeholder", "coördinator"}, true));
//		categorieRepo.insert(new Categorie("Datasources", "icon4", new String[] {"manager", "coördinator"}, false));
//		categorieRepo.insert(new Categorie("Account", "icon5", new String[] {"manager", "stakeholder", "coördinator"}, false));
//	}
}
