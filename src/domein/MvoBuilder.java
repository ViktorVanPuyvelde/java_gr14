package domein;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import exceptions.InformationRequiredException;

public class MvoBuilder
{
	protected Mvo mvo;
	protected Set<RequiredElement> requiredElements;
	protected Map<String, String> errorMap;

	public Mvo getMvo() throws InformationRequiredException
	{
		this.requiredElements = new HashSet<>();
		this.errorMap = new HashMap<>();

		if (this.mvo.getName() == null || this.mvo.getName().isEmpty())
		{
			this.errorMap.put("lblErrorNaam", "Naam is vereist.");
		}
		if (this.mvo.getDatasource() == null)
		{
			this.errorMap.put("lblErrorDatabron", "Er moet één databron aangeduid zijn.");
		}
		if (this.mvo.getSdg() == null)
		{
			this.errorMap.put("lblErrorSdg", "Er moet één SDG aangeduid zijn.");
		}
		if (this.mvo.getInfo().equals("null") || this.mvo.getInfo().isEmpty())
		{
			this.errorMap.put("lblErrorEenheid", "Er moet een eenheid meegegeven worden.");
		}
		if (this.mvo.getGoalValue() < 0)
		{
			this.errorMap.put("lblErrorDoel", "Het doel moet groter dan ");
		}

		if (!this.errorMap.isEmpty())
		{
			throw new InformationRequiredException(
					"De MVO-doelstelling kan niet aangemaakt worden, omdat sommige velden niet ingevuld zijn.",
					errorMap);
		}

		return this.mvo;
	}

	public void createNewMvo()
	{
		this.mvo = new Mvo();
	}

	public void buildId()
	{
		this.mvo.setId(UUID.randomUUID().toString());
	}

	public void buildName(String name)
	{
		this.mvo.setName(name);
	}

	public void buildDatasource(Datasource datasource)
	{
		this.mvo.setDatasource(datasource);
	}

	public void buildSdg(Sdg sdg)
	{
		this.mvo.setSdg(sdg);
	}

	public void buildSuperMvo(Mvo superMvo)
	{
		this.mvo.setSuperMvo(superMvo);
	}

	public void buildInfo(List<String> info)
	{
		this.mvo.setInfo(info);
	}

	public void buildGoalValue(int goalValue)
	{
		this.mvo.setGoalValue(goalValue);
	}
}
