package domein;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class CategorieBuilder
{
	protected Categorie categorie;
	protected Set<RequiredElement> requiredElements;

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
