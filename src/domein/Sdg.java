package domein;

public class Sdg
{

	private String id;
	private String name;
	private String image;

	/**
	 * 
	 * @param name
	 * @param image
	 */
	public Sdg(String name, String image)
	{
		this.name = name;
		this.image = image;
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

}