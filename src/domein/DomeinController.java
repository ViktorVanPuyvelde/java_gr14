package domein;

import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;

public class DomeinController {

	private UserBeheerder ub;
	
	public DomeinController() {
		this.ub = new UserBeheerder();
	}

	public void meldAan(String email, String paswoord) throws APIException, Auth0Exception {
		ub.meldAan(email, paswoord);
	}

	public void meldAf() {
		ub.meldAf();
	}

}