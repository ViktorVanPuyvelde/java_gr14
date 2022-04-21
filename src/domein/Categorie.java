package domein;

import java.util.List;

public class Categorie implements CRUD {

	private String id;
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
	public Categorie(String name, String iconName, List<String> roles) {
		// TODO - implement Categorie.Categorie
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

}