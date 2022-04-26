package domein;
public class Datasource implements CRUD {

	private String id;
	private String name;
	private Boolean flag;

	/**
	 * 
	 * @param name
	 */
	public Datasource(String name) {
		// TODO - implement Datasource.Datasource
		throw new UnsupportedOperationException();
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
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}