package domein;

import java.util.List;

public class Co�rdinatorApplicatie {

	private List<Categorie> categoei�n;
	private List<Mvo> mvos;
	private List<Mvo> datasource;

	public Co�rdinatorApplicatie() {
		// TODO - implement Co�rdinatorApplicatie.Co�rdinatorApplicatie
		throw new UnsupportedOperationException();
	}

	public List<Datasource> geefDatasources() {
		// TODO - implement Co�rdinatorApplicatie.geefDatasources
		throw new UnsupportedOperationException();
	}

	public List<Mvo> geefMvos() {
		// TODO - implement Co�rdinatorApplicatie.geefMvos
		throw new UnsupportedOperationException();
	}

	public List<Categorie> geefCategori�n() {
		// TODO - implement Co�rdinatorApplicatie.geefCategori�n
		throw new UnsupportedOperationException();
	}

	public Boolean updateApp() {
		// TODO - implement Co�rdinatorApplicatie.updateApp
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param flag
	 */
	public Datasource updateDatasource(String id, String name, Boolean flag) {
		// TODO - implement Co�rdinatorApplicatie.updateDatasource
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 * @param datasource
	 */
	/*
	public void addDatasource(String name, Filedatasource) {
		// TODO - implement Co�rdinatorApplicatie.addDatasource
		throw new UnsupportedOperationException();
	}
	*/

	/**
	 * 
	 * @param id
	 */
	public void deleteDatasource(String id) {
		// TODO - implement Co�rdinatorApplicatie.deleteDatasource
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 * @param name
	 */
	public Mvo updateMvo(String id, String name) {
		// TODO - implement Co�rdinatorApplicatie.updateMvo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 * @param datasource
	 * @param drempelwaarde
	 */
	public void addMvo(String name, Datasource datasource, int drempelwaarde) {
		// TODO - implement Co�rdinatorApplicatie.addMvo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public void deleteMvo(String id) {
		// TODO - implement Co�rdinatorApplicatie.deleteMvo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param iconName
	 * @param roles
	 */
	public Categorie updateCategorie(String id, String name, String iconName, List<String> roles) {
		// TODO - implement Co�rdinatorApplicatie.updateCategorie
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 * @param iconName
	 * @param roles
	 */
	public void addCategorie(String name, String iconName, List roles) {
		// TODO - implement Co�rdinatorApplicatie.addCategorie
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public void deleteCategorie(String id) {
		// TODO - implement Co�rdinatorApplicatie.deleteCategorie
		throw new UnsupportedOperationException();
	}

}