package domein;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.json.simple.JSONArray;

import util.JSONArrayConverter;

@Entity
@Table(name = "category")
@NamedQueries(
{ @NamedQuery(name = "Categorie.alleCategoriën", query = "select c from Categorie c") })
public class Categorie implements CRUD, Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private String id;
	@Column(name = "category_name")
	private String name;
	private String iconName;
	@Convert(converter = JSONArrayConverter.class)
	private JSONArray roles;
	@Transient
	private List<Sdg> sdgs;

	/**
	 * 
	 * @param name
	 * @param iconName
	 * @param roles
	 */
	public Categorie(String name, String iconName, JSONArray roles)
	{
		setName(name);
		setIconName(iconName);
		setRoles(roles);
	}

	protected Categorie()
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

	public String getIconName()
	{
		return iconName;
	}

	public void setIconName(String iconName)
	{
		this.iconName = iconName;
	}

	public JSONArray getRoles()
	{
		return roles;
	}

	public void setRoles(JSONArray roles)
	{
		this.roles = roles;
	}

	public List<Sdg> getSdgs()
	{
		return sdgs;
	}

	public void setSdgs(List<Sdg> sdgs)
	{
		this.sdgs = sdgs;
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

	@Override
	public int hashCode()
	{
		return Objects.hash(iconName, id, name, roles, sdgs);
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
		Categorie other = (Categorie) obj;
		return Objects.equals(iconName, other.iconName) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(roles, other.roles)
				&& Objects.equals(sdgs, other.sdgs);
	}

	@Override
	public String toString()
	{
		return String.format("id: %s, naam: %s, iconName: %s%n", getId(), getName(), getIconName());
	}
}