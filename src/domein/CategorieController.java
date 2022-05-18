package domein;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import exceptions.InformationRequiredException;
import repository.CategorieDao;
import repository.CategorieDaoJpa;

public class CategorieController
{
	private CategorieDao categorieRepo;
	private List<Categorie> cats;
    private PropertyChangeSupport subject;

	public CategorieController()
	{
		subject = new PropertyChangeSupport(this);
		setCategorieRepo(new CategorieDaoJpa());
		cats = categorieRepo.findAll();
		//populateDB();
	}
	
	public List<Categorie> geefCategorien() {
		return Collections.unmodifiableList(cats);
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
		List<Categorie> oldCats = new ArrayList<>(cats);
		Categorie newCategorie = createCategorie(null, name, iconName, roles, sdgs);
		CategorieDaoJpa.startTransaction();
		categorieRepo.insert(newCategorie);
		CategorieDaoJpa.commitTransaction();
		cats.add(newCategorie);
        subject.firePropertyChange("cats", oldCats, cats);
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
	
	//nieuwe luisteraar inschrijven
    public void addPropertyChangeListener(PropertyChangeListener pcl) { 
        subject.addPropertyChangeListener(pcl);
        pcl.propertyChange(
        		new PropertyChangeEvent(pcl, "cats", cats, cats));
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        subject.removePropertyChangeListener(pcl);
    }

//	private void populateDB() {
//		categorieRepo.insert(new Categorie("Profit", "icon1", new String[] {"manager", "stakeholder", "coördinator"}, true));
//		categorieRepo.insert(new Categorie("Planet", "icon2", new String[] {"manager", "stakeholder", "coördinator"}, true));
//		categorieRepo.insert(new Categorie("People", "icon3", new String[] {"manager", "stakeholder", "coördinator"}, true));
//		categorieRepo.insert(new Categorie("Datasources", "icon4", new String[] {"manager", "coördinator"}, false));
//		categorieRepo.insert(new Categorie("Account", "icon5", new String[] {"manager", "stakeholder", "coördinator"}, false));
//	}
}
