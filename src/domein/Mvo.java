package domein;

import java.util.Date;
import java.util.List;

public class Mvo implements CRUD {

	private String id;
	private Date date;
	private List<String> data;
	private int quarter;
	private List<String> mvo_data;
	private String name;

	/**
	 * 
	 * @param name
	 * @param datasource
	 */
	public Mvo(String name, Datasource datasource) {
		// TODO - implement Mvo.Mvo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 * @param datasource
	 * @param drempelwaarde
	 */
	public Mvo(String name, Datasource datasource, int drempelwaarde) {
		// TODO - implement Mvo.Mvo
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