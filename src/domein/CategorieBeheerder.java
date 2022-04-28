package domein;

import java.util.List;

public class CategorieBeheerder
{
	private DatabankConnectie dc;

	public CategorieBeheerder()
	{
		this.dc = new DatabankConnectie();
		dc.initializePersistentie();
	}

	public void closePersistentie()
	{
		dc.closePersistentie();
	}

	public List<Categorie> geefAlleCategorienJPA()
	{
		return dc.getEm().createNamedQuery("Categorie.alleCategoriën", Categorie.class).getResultList();
	}

	public void addCategorie(Categorie c)
	{
		dc.getEm().getTransaction().begin();
		dc.getEm().persist(c);
		dc.getEm().getTransaction().commit();
	}
}
