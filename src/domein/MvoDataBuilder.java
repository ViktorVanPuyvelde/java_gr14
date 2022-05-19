package domein;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import exceptions.InformationRequiredException;

public class MvoDataBuilder
{
	protected MvoData mvoData;
	protected Set<RequiredElement> requiredElements;

	public MvoData getMvoData() throws InformationRequiredException
	{
		this.requiredElements = new HashSet<>();

		if (this.mvoData.getDatum() == null )
		{
			requiredElements.add(RequiredElement.DateRequired);
		}
		if (this.mvoData.getMvo_id() == null)
		{
			requiredElements.add(RequiredElement.MvoRequired);
		}
		if (this.mvoData.getQuarter() < 0)
		{
			requiredElements.add(RequiredElement.QuarterRequired);
		}
		if (this.mvoData.getWaardeInt() < 0)
		{
			requiredElements.add(RequiredElement.ValueRequired);
		}

		if (!this.requiredElements.isEmpty())
		{
			throw new InformationRequiredException(
					"Datasource kan niet omgezet worden omdat er zich een fout bevind in de data",
					requiredElements);
		}

		return this.mvoData;
	}

	public void createNewMvoData()
	{
		this.mvoData = new MvoData();
	}

	public void buildId()
	{
		this.mvoData.setId(UUID.randomUUID().toString());
	}

	public void buildMvo(Mvo Mvo)
	{
		this.mvoData.setMvo_id(Mvo);
	}
	
	public void buildDatum(Date date)
	{
		this.mvoData.setDatum(date);
	}
	
	public void buildQuarter(int quarter)
	{
		this.mvoData.setQuarter(quarter);
	}
	
	public void buildWaarde(String waarde)
	{
		this.mvoData.setWaarde(waarde);
	}
	

}
