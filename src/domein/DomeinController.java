package domein;

import java.util.List;

/*import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.TokenHolder;
import com.auth0.json.auth.UserInfo;
import com.auth0.net.AuthRequest;
import com.auth0.net.Request;
import io.github.cdimascio.dotenv.Dotenv;*/

public class DomeinController {

//	private CoördinatorApplicatie appManager;
	private User coordinator;
	private CategorieController catController;
	
	public DomeinController(){
		this.catController = new CategorieController();
	}
	
	/*
	 * public void meldAan(String email, String paswoord) throws APIException,
	 * Auth0Exception { Dotenv dotenv = Dotenv.load();
	 * 
	 * //https://github.com/auth0/auth0-java AuthAPI auth = new
	 * AuthAPI("dev-uzkyml7z.us.auth0.com", dotenv.get("CLIENT_ID"),
	 * dotenv.get("CLIENT_SECRET"));
	 * 
	 * @SuppressWarnings("deprecation") AuthRequest request = auth.login(email,
	 * paswoord) .setAudience("https://SDPIIGR14-R.com")
	 * .setScope("openid contacts");
	 * 
	 * TokenHolder holder = request.execute(); Request<UserInfo> request2 =
	 * auth.userInfo(holder.getAccessToken()); UserInfo info = request2.execute();
	 * coordinator = new User(info.getValues().get("email").toString());
	 * 
	 * //TODO: Rol ophalen uit db, enkel coordinator kan inloggen
	 * 
	 * System.out.println(info.getValues().toString());
	 * System.out.println(coordinator.getEmail()); }
	 * 
	 * public void meldAf(){ Dotenv dotenv = Dotenv.load();
	 * 
	 * AuthAPI auth = new AuthAPI("dev-uzkyml7z.us.auth0.com",
	 * dotenv.get("CLIENT_ID"), dotenv.get("CLIENT_SECRET"));
	 * 
	 * auth.logoutUrl("http://localhost:3000/", true);
	 * 
	 * coordinator = null;
	 * 
	 * System.out.println("Afgemeld"); }
	 */
	public List<Categorie> geefAlleCategories(){
		return catController.geefCategorien();
	}
	
	public Categorie raadpleegCategorie(Categorie c) {
		return catController.raadpleegCategorie(c);
	}
	
}