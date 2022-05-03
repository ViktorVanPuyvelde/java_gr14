package domein;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Mvo implements CRUD
{

	private String id;
	private Date date;
	private List<String> data;
	private int quarter;
	private List<String> mvo_data;
	private String name;

	/**
	 * 
	 * @param name
	 * @param datasource
	 */
	public Mvo(String name, Datasource datasource)
	{
		this.setName(name);
	}

	/**
	 * 
	 * @param name
	 * @param datasource
	 * @param drempelwaarde
	 */
	public Mvo(String name, Datasource datasource, int drempelwaarde)
	{
		// TODO - implement Mvo.Mvo
		throw new UnsupportedOperationException();
	}

	@Override
	public void read()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void create()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void delete()
	{
		// TODO Auto-generated method stub

	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(data, date, id, mvo_data, name, quarter);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mvo other = (Mvo) obj;
		return Objects.equals(data, other.data) && Objects.equals(date, other.date) && Objects.equals(id, other.id)
				&& Objects.equals(mvo_data, other.mvo_data) && Objects.equals(name, other.name)
				&& quarter == other.quarter;
	}

}