package domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.Gson;

@Entity
@Table(name = "category")
@NamedQueries(
{ @NamedQuery(name = "Categorie.sdgVoorCat", query = "select s from Categorie c INNER JOIN c.sdgs s WHERE c.name = :catNaam") })
public class Categorie implements CRUD, Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private int id;
	@Column(name = "category_name")
	private String name;
	private String iconName;
	private String roles;
	
	@OneToMany
	private List<Sdg> sdgs;
	private boolean isCategory;

	/**
	 * 
	 * @param name
	 * @param iconName
	 * @param roles
	 */
	public Categorie(String name, String iconName, List<String> roles, boolean isCategory)
	{
		setName(name);
		setIconName(iconName);
		setRoles(roles);
		setCategory(isCategory);
		setSdgs(new ArrayList<>());
	}

	protected Categorie()
	{

	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
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

	public String getRoles()
	{
		return roles;
	}

	public void setRoles(List<String> roles)
	{
		Gson gson = new Gson();
		String rolesAsGson = gson.toJson(roles);
		this.roles = rolesAsGson;
	}

	public List<Sdg> getSdgs()
	{
		return sdgs;
	}

	public void setSdgs(List<Sdg> sdgs)
	{
		this.sdgs = sdgs;
	}

	public boolean isCategory()
	{
		return isCategory;
	}

	public void setCategory(boolean isCategory)
	{
		this.isCategory = isCategory;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public void setRoles(String roles)
	{
		this.roles = roles;
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
		return Objects.hash(iconName, id, name/* , roles, sdgs */);
	}

//	@Override
//	public boolean equals(Object obj)
//	{
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Categorie other = (Categorie) obj;
//		return Objects.equals(iconName, other.iconName) && Objects.equals(id, other.id)
//				&& Objects.equals(name, other.name) && Objects.equals(roles, other.roles)
//				&& Objects.equals(sdgs, other.sdgs);
//	}

	@Override
	public String toString()
	{
		return String.format("id: %s, naam: %s, iconName: %s%n", getId(), getName(), getIconName());
	}
}