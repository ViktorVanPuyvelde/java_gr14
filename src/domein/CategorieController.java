package domein;

import java.util.List;

public class CategorieController
{
	private CategorieBeheerder cb = new CategorieBeheerder();

	public List<String> geefCategorien()
	{
		return cb.geefAlleCategorienJPA().stream().map(Categorie::toString).toList();
	}

	public void close()
	{
		cb.closePersistentie();
	}
}
