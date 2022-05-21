package domein;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "datasource")
@NamedQueries(
{ 		@NamedQuery(name = "Datasource.alleDatasources", query = "select d from Datasource d"),
		@NamedQuery(name = "Datasource.geefDatasourceDoorNaam", query = "select d from Datasource d where d.name = :naam"),
		@NamedQuery(name = "Datasource.geefDatasourceMetId", query = "SELECT d from Datasource d where d.id = :datasource_id")})
public class Datasource implements Serializable
{
	

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "datasource_id")
	private String id;
	@Column(name = "datasource_name")
	private String name;
	@Column(name = "flag")
	private Boolean flag;

	public Datasource(String name, boolean flag)
	{
		setId(UUID.randomUUID().toString());
		setName(name);
		setFlag(flag);
	}

	protected Datasource()
	{

	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Boolean getFlag()
	{
		return flag;
	}

	public void setFlag(Boolean flag)
	{
		this.flag = flag;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Datasource other = (Datasource) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString()
	{
		return String.format("%s", name);
	}

}