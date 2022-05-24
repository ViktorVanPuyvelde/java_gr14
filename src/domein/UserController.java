package domein;

import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.TokenHolder;
import com.auth0.json.auth.UserInfo;
import com.auth0.net.AuthRequest;
import com.auth0.net.Request;

import exceptions.IngelogdVerkeerdeRol;
import io.github.cdimascio.dotenv.Dotenv;
import repository.UserDaoJpa;

public class UserController
{
	private User coordinator;
	private UserDaoJpa repository;

	public UserController()
	{
		repository = new UserDaoJpa();
	}

	public void closePersistentie()
	{
		UserDaoJpa.closePersistency();
	}

	public User getCoordinator()
	{
		return coordinator;
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
		coordinator = new User(info.getValues().get("email").toString(), id,
				info.getValues().get("nickname").toString());

		// enkel coordinator kan inloggen
		User u = repository.get(id);
		if (!u.getRole().equals("co\u00f6rdinator"))
		{
			meldAf();
			throw new IngelogdVerkeerdeRol();
		} else
		{
			coordinator.setRole(u.getRole());
		}
	}

	public void meldAf()
	{
		coordinator = null;
	}

	public String getUserInfo()
	{
		return coordinator.getName();
	}
}
