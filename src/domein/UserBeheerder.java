package domein;

import javax.persistence.EntityManager;

import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.TokenHolder;
import com.auth0.json.auth.UserInfo;
import com.auth0.net.AuthRequest;
import com.auth0.net.Request;

import io.github.cdimascio.dotenv.Dotenv;
import util.JPAUtil;

public class UserBeheerder
{

	private EntityManager em;
	private User coordinator;

	public void openPersistentie()
	{
		em = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	public void closePersistentie()
	{
		em.close();
		JPAUtil.getEntityManagerFactory().close();
	}

	public void meldAan(String email, String paswoord) throws APIException, Auth0Exception
	{
		Dotenv dotenv = Dotenv.load();

		// https://github.com/auth0/auth0-java

		AuthAPI auth = new AuthAPI("dev-uzkyml7z.us.auth0.com", dotenv.get("CLIENT_ID"), dotenv.get("CLIENT_SECRET"));

		@SuppressWarnings("deprecation")
		AuthRequest request = auth.login(email, paswoord).setAudience("https://SDPIIGR14-R.com")
				.setScope("openid contacts");

		TokenHolder holder = request.execute();
		Request<UserInfo> request2 = auth.userInfo(holder.getAccessToken());
		UserInfo info = request2.execute();
		String id = info.getValues().get("sub").toString().substring(6);
		coordinator = new User(info.getValues().get("email").toString(), id);

		// enkel coordinator kan inloggen
		openPersistentie();

		User u = em.createNamedQuery("User.findRole", User.class).setParameter("id", id).getSingleResult();

		if (!u.getRole().equals("coördinator"))
		{
			meldAf();
		} else
		{
			coordinator.setRole(u.getRole());
		}

		closePersistentie();
	}

	public void meldAf()
	{
//		Dotenv dotenv = Dotenv.load();
//
//		AuthAPI auth = new AuthAPI("dev-uzkyml7z.us.auth0.com", dotenv.get("CLIENT_ID"), dotenv.get("CLIENT_SECRET"));
//
//		auth.logoutUrl("http://localhost:3000/", true);
		coordinator = null;
	}
}
