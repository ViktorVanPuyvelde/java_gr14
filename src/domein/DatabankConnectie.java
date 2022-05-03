package domein;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabankConnectie
{
	public final String PERSISTENCE_UNIT_NAME = "GR14";
	private EntityManager em;
	private EntityManagerFactory emf;

	public void initializePersistentie()
	{
		openPersistentie();
	}

	private void openPersistentie()
	{
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = emf.createEntityManager();
	}

	public void closePersistentie()
	{
		em.close();
		emf.close();
	}

	public EntityManager getEm()
	{
		return em;
	}

	public EntityManagerFactory getEmf()
	{
		return emf;
	}
}