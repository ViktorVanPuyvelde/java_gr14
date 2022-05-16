package domein;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import exceptions.InformationRequiredException;

public class DatasourceBuilder
{
	public Datasource datasource;
	protected Set<RequiredElement> requiredElements;

	public Datasource getDatasource() throws InformationRequiredException
	{
		this.requiredElements = new HashSet<>();

		if (this.datasource.getName() == null || this.datasource.getName().isEmpty())
		{
			requiredElements.add(RequiredElement.NameRequired);
		}
		if (this.datasource.getFlag() == null)
		{
			requiredElements.add(RequiredElement.FlagRequired);
		}

		if (!this.requiredElements.isEmpty())
		{
			throw new InformationRequiredException(
					"De datasource kan niet aangemaakt worden, omdat sommige velden niet ingevuld zijn",
					requiredElements);
		}

		return this.datasource;
	}

	public void createNewDatasource()
	{
		this.datasource = new Datasource();
	}

	public void buildId()
	{
		datasource.setId(UUID.randomUUID().toString());
	}

	public void buildName(String name)
	{
		datasource.setName(name);
	}

	public void buildFlag(boolean flag)
	{
		datasource.setFlag(flag);
	}
}
