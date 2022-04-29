package ui;

import java.util.Scanner;

import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;

import domein.CategorieController;
import domein.DomeinController;

public class ConsoleLogin
{
	private CategorieController cc;

	public static void main(String[] args)
	{
		DomeinController dc = new DomeinController();

		Scanner sc = new Scanner(System.in);
//		new ConsoleLogin().run();

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
	}

//	public void run()
//	{
//		cc = new CategorieController();
//		System.out.printf("Categorie: %s%n", cc.geefCategorien());
//	}

}
