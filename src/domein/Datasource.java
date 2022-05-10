package domein;

import java.util.UUID;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="datasource")
@NamedQueries(
{ @NamedQuery(name = "Datasource.alleDatasources", query = "select d from Datasource d") })
public class Datasource implements Serializable, CRUD {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "datasource_id")
	private String id;
	@Column(name = "datasource_name")
	private String name;

	
	private Boolean flag;

	/**
	 * 
	 * @param name
	 */
	public Datasource(String name)
	{
		setId(UUID.randomUUID().toString());
		this.name = name;
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



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void read() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

}