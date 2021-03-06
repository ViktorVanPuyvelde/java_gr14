package domein;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import exceptions.InformationRequiredException;

public class MvoBuilder
{
	protected Mvo mvo;
	protected Map<String, String> errorMap;

	public Mvo getMvo() throws InformationRequiredException
	{
		this.errorMap = new HashMap<>();

		if (this.mvo.getName() == null || this.mvo.getName().isEmpty())
		{
			this.errorMap.put("lblErrorNaam", "Naam is vereist.");
		}
		if (this.mvo.getSdg() == null)
		{
			this.errorMap.put("lblErrorSdg", "Er moet \u00E9\u00E9n SDG aangeduid zijn.");
		}
		if (this.mvo.getEenheid() == null || this.mvo.getEenheid().isEmpty())
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

	public void setMvo(Mvo mvo)
	{
		this.mvo = mvo;
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

	public void buildEenheid(String eenheid)
	{
		this.mvo.setEenheid(eenheid);
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
