package domein;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CategorieController
{
	public final String PERSISTENCE_UNIT_NAME = "GR14";
	private EntityManager em;
	private EntityManagerFactory emf;

	public CategorieController()
	{
		initializePersistentie();
	}

	public void initializePersistentie()
	{
		openPersistentie();
	}

	public void openPersistentie()
	{
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = emf.createEntityManager();
	}
}
