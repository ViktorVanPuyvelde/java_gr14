package domein;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sdg")
public class Sdg implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "sdg_id")
	private String id;
	@Column(name = "sdg_name")
	private String name;
	@Column(name = "sdg_image")
	private String image;
	@OneToMany
	private List<Mvo> mvos;

	/**
	 * 
	 * @param name
	 * @param image
	 */
	public Sdg(String name, String image)
	{
		setId(UUID.randomUUID().toString());
		setName(name);
		setImage(image);
	}

	protected Sdg()
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

	public String getImage()
	{
		return image;
	}

	public void setImage(String image)
	{
		this.image = image;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, image, name);
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
		Sdg other = (Sdg) obj;
		return Objects.equals(id, other.id) && Objects.equals(image, other.image) && Objects.equals(name, other.name);
	}

	@Override
	public String toString()
	{
		return String.format("%s", getName());
	}

}