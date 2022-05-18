package domein;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import exceptions.InformationRequiredException;

public class CategorieBuilder
{
	protected Categorie categorie;
	protected Set<RequiredElement> requiredElements;

	public Categorie getCategorie() throws InformationRequiredException
	{
		this.requiredElements = new HashSet<>();

		if (this.categorie.getName() == null || this.categorie.getName().isEmpty())
		{
			requiredElements.add(RequiredElement.NameRequired);
		}
		if (this.categorie.getSdgs().isEmpty() || this.categorie.getSdgs() == null)
		{
			requiredElements.add(RequiredElement.SdgRequired);
		}
		if (this.categorie.getRoles().isEmpty() || this.categorie.getRoles() == null)
		{
			this.requiredElements.add(RequiredElement.RolesRequired);
		}
		if (this.categorie.getIconName().isEmpty() || this.categorie.getIconName() == null)
		{
			this.requiredElements.add(RequiredElement.IconNameRequired);
		}

		if (!this.requiredElements.isEmpty())
		{
			throw new InformationRequiredException(
					"Categorie kan niet aangemaakt worden, omdat sommige velden niet ingevuld zijn", requiredElements);
		}

		return this.categorie;
	}

	public void createNewCategorie()
	{
		this.categorie = new Categorie();
	}

	public void buildId()
	{
		this.categorie.setId(UUID.randomUUID().toString());
	}

	public void buildName(String name)
	{
		this.categorie.setName(name);
	}

	public void buildIconName(String iconName)
	{
		this.categorie.setIconName(iconName);
	}

	public void buildRoles(List<String> roles)
	{
		this.categorie.setRoles(roles);
	}

	public void buildCategory(boolean isCategory)
	{
		this.categorie.setCategory(isCategory);
	}

	public void buildSdgs(List<Sdg> sdgs)
	{
		this.categorie.setSdgs(sdgs);
	}
}
