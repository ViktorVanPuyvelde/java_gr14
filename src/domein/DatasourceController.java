package domein;

import java.util.List;

public class DatasourceController
{
	private DatasourceBeheerder cb = new DatasourceBeheerder();

	public List<String> geefCategorien()
	{
		return cb.geefAlleDatasourcesJPA().stream().map(Datasource::toString).toList();
	}

	public void close()
	{
		cb.closePersistentie();
	}
}