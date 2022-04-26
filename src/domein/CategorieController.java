package domein;

import java.util.List;

import org.json.JSONArray;

public class CategorieController
{
	private CategorieBeheerder cb = new CategorieBeheerder();

	public List<String> geefCategorien()
	{
		return cb.geefAlleCategorienJPA().stream().map(Categorie::toString).toList();
	}

	public void voegCategorieToe(String name, String iconName, JSONArray roles)
	{
		cb.addCategorie(new Categorie(name, iconName, roles));
	}

	public void close()
	{
		cb.closePersistentie();
	}
}
