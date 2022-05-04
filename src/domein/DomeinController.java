package domein;

import java.util.List;

import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;

public class DomeinController {

	private CategorieController catController;
	private UserBeheerder ub;
	
	public DomeinController() {
		this.ub = new UserBeheerder();
		this.catController = new CategorieController();
	}
	
	public List<Categorie> geefAlleCategories(){
		return catController.geefCategorien();
	}
	
	public Categorie raadpleegCategorie(Categorie c) {
		return catController.raadpleegCategorie(c);
	}	

	public void meldAan(String email, String paswoord) throws APIException, Auth0Exception {
		ub.meldAan(email, paswoord);
	}

	public void meldAf() {
		ub.meldAf();
	}

}