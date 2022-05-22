package domein;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import exceptions.InformationRequiredException;

public class DatasourceBuilder
{
	public Datasource datasource;
	protected Map<String, String> errorMap;

	public Datasource getDatasource() throws InformationRequiredException
	{
		this.errorMap = new HashMap<>();

		if (this.datasource.getName() == null || this.datasource.getName().isEmpty())
		{
			this.errorMap.put("lblErrorNaam", "Naam is vereist.");
		}
//		if (this.datasource.getFlag() == null)
//		{
//
//		}
//		geen idee waarom dit nog is

		if (!this.errorMap.isEmpty())
		{
			throw new InformationRequiredException(
					"De datasource kan niet aangemaakt worden, omdat sommige velden niet ingevuld zijn", errorMap);
		}

		return this.datasource;
	}

	public void createNewDatasource()
	{
		this.datasource = new Datasource();
	}

	public void setDatasource(Datasource datasource) {
		this.datasource = datasource;
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
