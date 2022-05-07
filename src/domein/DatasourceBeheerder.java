package domein;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatasourceBeheerder
{
	public final String PERSISTENCE_UNIT_NAME = "GR14";
	private EntityManager em;
	private EntityManagerFactory emf;

	public DatasourceBeheerder()
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

	public void closePersistentie()
	{
		em.close();
		emf.close();
	}

	public List<Datasource> geefAlleDatasourcesJPA()
	{
		return em.createNamedQuery("Datasource.alleDatasources", Datasource.class).getResultList();
	}
}
