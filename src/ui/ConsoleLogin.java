package ui;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;

import domein.DomeinController;
import domein.User;
import util.JPAUtil;

public class ConsoleLogin
{

	public static void main(String[] args)
	{
		DomeinController dc = new DomeinController();

		Scanner sc = new Scanner(System.in);

		System.out.println("Email:");
		String email = sc.next();

		System.out.println("Paswoord:");
		String paswoord = sc.next();

		try {
			dc.meldAan(email, paswoord);
		} catch (APIException e) {
			e.printStackTrace();
		} catch (Auth0Exception e) {
			e.printStackTrace();
		}
		
		dc.meldAf();

//		User u = new User("161684319461fezs1", "test@test.be"/* new JSONArray() */);
//
//		List<User> lijst;
//		EntityManager en = JPAUtil.getEntityManagerFactory().createEntityManager();
//
//		en.getTransaction().begin();
//
//		TypedQuery<User> q = en.createNamedQuery("User.findAll", User.class);
//		lijst = q.getResultList();
//
//		System.out.println(lijst);
//		// en.persist(u);
//
//		en.getTransaction().commit();
//
//		en.close();
//
//		JPAUtil.getEntityManagerFactory().close();
	}

}
