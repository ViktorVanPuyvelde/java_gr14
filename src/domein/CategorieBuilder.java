package domein;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import exceptions.InformationRequiredException;

public class CategorieBuilder
{
	protected Categorie categorie;
	protected Map<String, String> errorMap;
	protected boolean isCategorie;

	public Categorie getCategorie() throws InformationRequiredException
	{
		this.errorMap = new HashMap<>();

		if (this.categorie.getName() == null || this.categorie.getName().isEmpty())
		{
			this.errorMap.put("lblErrorName", "Naam is vereist.");
		}
		if (isCategorie)
		{
			if (this.categorie.getSdgs() == null || this.categorie.getSdgs().isEmpty())
			{
				this.errorMap.put("lblErrorSdg", "Minstens \u00E9\u00E9n SDG is vereist.");
			}
		}
		if (this.categorie.getRoles() == null || this.categorie.getRoles().equals("[]")
				|| this.categorie.getRoles().equals("null"))
		{
			this.errorMap.put("lblErrorRol", "Minstens \u00E9\u00E9n rol is vereist.");
		}
		if (this.categorie.getIconName() == null || this.categorie.getIconName().isEmpty())
		{
			this.errorMap.put("lblErrorPic", "De naam van een pictogram is vereist.");
		}

		if (!this.errorMap.isEmpty())
		{
			throw new InformationRequiredException(
					"Categorie kan niet aangemaakt worden, omdat sommige velden niet ingevuld zijn", errorMap);
		}

		return this.categorie;
	}

	public void createNewCategorie()
	{
		this.categorie = new Categorie();
	}

	public void setCategorie(Categorie c, boolean isCategorie)
	{
		this.categorie = c;
		this.isCategorie = isCategorie;
	}

	public void setIsCategorie(boolean isCategorie)
	{
		this.isCategorie = isCategorie;
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
