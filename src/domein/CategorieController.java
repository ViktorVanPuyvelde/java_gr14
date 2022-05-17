package domein;

import java.util.List;
import java.util.stream.Collectors;

import exceptions.InformationRequiredException;
import repository.CategorieDao;
import repository.CategorieDaoJpa;

public class CategorieController
{
	private CategorieDao categorieRepo;

	public CategorieController()
	{
		setCategorieRepo(new CategorieDaoJpa());
		// populateDB();
	}

	public List<Categorie> geefCategorien()
	{
		return categorieRepo.findAll();

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

	public void voegCategorieToe(String name, String iconName, List<String> roles, List<Sdg> sdgs)
			throws InformationRequiredException
	{
		Categorie newCategorie = createCategorie(null, name, iconName, roles, sdgs);
		CategorieDaoJpa.startTransaction();
		categorieRepo.insert(new Categorie(name, iconName, roles, true, sdgs));
		CategorieDaoJpa.commitTransaction();
	}

	public void pasCategorieAan(Categorie c) throws InformationRequiredException
	{
		CategorieBuilder cb = new CategorieBuilder();
		cb.setCategorie(c);
		Categorie updateCategorie = cb.getCategorie();
		CategorieDaoJpa.startTransaction();
		categorieRepo.update(updateCategorie);
		CategorieDaoJpa.commitTransaction();
	}

	private Categorie createCategorie(CategorieBuilder cb, String name, String iconName, List<String> roles,
			List<Sdg> sdgs) throws InformationRequiredException
	{
		if (cb == null)
		{
			cb = new CategorieBuilder();
		}
		cb.createNewCategorie();
		cb.buildId();
		cb.buildName(name);
		cb.buildIconName(iconName);
		cb.buildSdgs(sdgs);
		cb.buildRoles(roles);
		return cb.getCategorie();
	}

	public void close()
	{
		CategorieDaoJpa.closePersistency();
	}

	public List<Sdg> geefSdgsVoorCategorie(Categorie c)
	{
		List<Sdg> sdgs = categorieRepo.geefSdgVoorCategorie(c.getName());
		c.setSdgs(sdgs);
		return sdgs;
	}

//	private void populateDB() {
//		categorieRepo.insert(new Categorie("Profit", "icon1", new String[] {"manager", "stakeholder", "coördinator"}, true));
//		categorieRepo.insert(new Categorie("Planet", "icon2", new String[] {"manager", "stakeholder", "coördinator"}, true));
//		categorieRepo.insert(new Categorie("People", "icon3", new String[] {"manager", "stakeholder", "coördinator"}, true));
//		categorieRepo.insert(new Categorie("Datasources", "icon4", new String[] {"manager", "coördinator"}, false));
//		categorieRepo.insert(new Categorie("Account", "icon5", new String[] {"manager", "stakeholder", "coördinator"}, false));
//	}
}
