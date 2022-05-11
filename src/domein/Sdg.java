package domein;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sdg")
@NamedQueries(
{	@NamedQuery(name = "Mvo.geefSdgVoorMvo", query = "SELECT s FROM Mvo m INNER JOIN m.sdg s WHERE s.id LIKE :mvoSdgId AND m.id LIKE :mvoId")
	@NamedQuery(name = "Sdg.sdgVoorCat", query = "SELECT s FROM Sdg s INNER JOIN s.categorie c WHERE c.name = :catNaam")
})
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
	
	@OneToMany (mappedBy="sdg")
	private List<Mvo> mvos;
	
	@ManyToOne(targetEntity = Sdg.class)
	@JoinColumn(name = "super_sdg_id")
	private Sdg super_sdg;
	
	@ManyToOne(targetEntity = Categorie.class)
	@JoinColumn(name = "category_id")
	private Categorie categorie;
	
	// @ManyToOne
	// @JoinColumn(name= "category_id", nullable= false)
	// private Categorie categorie;
	/**
	 * 
	 * @param name
	 * @param image
	 */
	public Sdg(String name, String image, List<Mvo> mvos, Categorie categorie)
	{
		setId(UUID.randomUUID().toString());
		setName(name);
		setImage(image);
		setMvos(mvos);
		setCategorie(categorie);
	}

	protected Sdg()
	{

	}

	public List<Mvo> getMvos() {
		return mvos;
	}

	public void setMvos(List<Mvo> mvos) {
		this.mvos = mvos;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
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