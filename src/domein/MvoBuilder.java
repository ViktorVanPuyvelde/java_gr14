package domein;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import exceptions.InformationRequiredException;

public class MvoBuilder
{
	protected Mvo mvo;
	protected Set<RequiredElement> requiredElements;

	public Mvo getMvo() throws InformationRequiredException
	{
		this.requiredElements = new HashSet<>();

		if (this.mvo.getName() == null || this.mvo.getName().isEmpty())
		{
			requiredElements.add(RequiredElement.NameRequired);
		}
		
		if (this.mvo.getSdg() == null)
		{
			requiredElements.add(RequiredElement.SdgRequired);
		}
		if (this.mvo.getInfo().equals("null") || this.mvo.getInfo().isEmpty())
		{
			requiredElements.add(RequiredElement.InfoRequired);
		}
		if (this.mvo.getGoalValue() < 0)
		{
			requiredElements.add(RequiredElement.GoalValueRequired);
		}

		if (!this.requiredElements.isEmpty())
		{
			throw new InformationRequiredException(
					"De MVO-doelstelling kan niet aangemaakt worden, omdat sommige velden niet ingevuld zijn.",
					requiredElements);
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
	
	public void buildMvoData(List<MvoData> mvoData)
	{
		this.mvo.setMvo_data(mvoData);
	}
}
