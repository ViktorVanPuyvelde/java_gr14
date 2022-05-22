package domein;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
    private int createOrUpdateOrDelete = 0;
    
	public CategorieController()
	{
		subject = new PropertyChangeSupport(this);
		setCategorieRepo(new CategorieDaoJpa());
		cats = categorieRepo.findAll();
	}

	public List<Categorie> geefCategorien()
	{
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

	public Categorie voegCategorieToe(String name, String iconName, List<String> roles, List<Sdg> sdgs)
			throws InformationRequiredException
	{
		int old = createOrUpdateOrDelete;
		createOrUpdateOrDelete = 1;
		Categorie newCategorie = createCategorie(null, name, iconName, roles, sdgs);
		CategorieDaoJpa.startTransaction();
		categorieRepo.insert(newCategorie);
		CategorieDaoJpa.commitTransaction();
		cats.add(newCategorie);
        subject.firePropertyChange("createOrUpdateOrDelete", old, createOrUpdateOrDelete);
		return newCategorie;
	}

	public void pasCategorieAan(Categorie c, boolean isCategorie) throws InformationRequiredException
	{
		int old = createOrUpdateOrDelete;
		createOrUpdateOrDelete = 2;
		CategorieBuilder cb = new CategorieBuilder();
		cb.setCategorie(c, isCategorie);
		Categorie updateCategorie = cb.getCategorie();
		CategorieDaoJpa.startTransaction();
		categorieRepo.update(updateCategorie);
		CategorieDaoJpa.commitTransaction();
		cats.set(cats.indexOf(c), updateCategorie);
        subject.firePropertyChange("createOrUpdateOrDelete", old, createOrUpdateOrDelete);
	}

	public void verwijderCategorie(Categorie c)
	{
		int old = createOrUpdateOrDelete;
		createOrUpdateOrDelete = 3;
		CategorieDaoJpa.startTransaction();
		categorieRepo.delete(c);
		CategorieDaoJpa.commitTransaction();
		cats.remove(c);
        subject.firePropertyChange("createOrUpdateOrDelete", old, createOrUpdateOrDelete);
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
		cb.buildCategory(true);
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
        		new PropertyChangeEvent(pcl, "createOrUpdateOrDelete", createOrUpdateOrDelete, 0));
    }

	public void removePropertyChangeListener(PropertyChangeListener pcl)
	{
		subject.removePropertyChangeListener(pcl);
	}

	
	public Categorie getCategorie(String naam) {
		return cats.stream().filter(c -> c.getName().equals(naam)).findAny().get();
	}
}
