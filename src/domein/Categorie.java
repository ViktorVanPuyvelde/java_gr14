package domein;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
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
	private List<String> roles;
	private List<Sdg> sdgs;

	/**
	 * 
	 * @param name
	 * @param iconName
	 * @param roles
	 */
	public Categorie(String name, String iconName, List<String> roles)
	{
		// TODO - implement Categorie.Categorie
		throw new UnsupportedOperationException();
	}

	protected Categorie()
	{

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
}