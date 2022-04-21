package domein;

import java.util.List;

public class CoördinatorApplicatie {

	private List<Categorie> categoeiën;
	private List<Mvo> mvos;
	private List<Mvo> datasource;

	public CoördinatorApplicatie() {
		// TODO - implement CoördinatorApplicatie.CoördinatorApplicatie
		throw new UnsupportedOperationException();
	}

	public List<Datasource> geefDatasources() {
		// TODO - implement CoördinatorApplicatie.geefDatasources
		throw new UnsupportedOperationException();
	}

	public List<Mvo> geefMvos() {
		// TODO - implement CoördinatorApplicatie.geefMvos
		throw new UnsupportedOperationException();
	}

	public List<Categorie> geefCategoriën() {
		// TODO - implement CoördinatorApplicatie.geefCategoriën
		throw new UnsupportedOperationException();
	}

	public Boolean updateApp() {
		// TODO - implement CoördinatorApplicatie.updateApp
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param flag
	 */
	public Datasource updateDatasource(String id, String name, Boolean flag) {
		// TODO - implement CoördinatorApplicatie.updateDatasource
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 * @param datasource
	 */
	/*
	public void addDatasource(String name, Filedatasource) {
		// TODO - implement CoördinatorApplicatie.addDatasource
		throw new UnsupportedOperationException();
	}
	*/

	/**
	 * 
	 * @param id
	 */
	public void deleteDatasource(String id) {
		// TODO - implement CoördinatorApplicatie.deleteDatasource
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 * @param name
	 */
	public Mvo updateMvo(String id, String name) {
		// TODO - implement CoördinatorApplicatie.updateMvo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 * @param datasource
	 * @param drempelwaarde
	 */
	public void addMvo(String name, Datasource datasource, int drempelwaarde) {
		// TODO - implement CoördinatorApplicatie.addMvo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public void deleteMvo(String id) {
		// TODO - implement CoördinatorApplicatie.deleteMvo
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
		// TODO - implement CoördinatorApplicatie.updateCategorie
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 * @param iconName
	 * @param roles
	 */
	public void addCategorie(String name, String iconName, List roles) {
		// TODO - implement CoördinatorApplicatie.addCategorie
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public void deleteCategorie(String id) {
		// TODO - implement CoördinatorApplicatie.deleteCategorie
		throw new UnsupportedOperationException();
	}

}