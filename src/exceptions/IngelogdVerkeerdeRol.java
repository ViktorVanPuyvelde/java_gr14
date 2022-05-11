package exceptions;

public class IngelogdVerkeerdeRol extends IllegalArgumentException
{
	public IngelogdVerkeerdeRol()
	{
		super("Enkel de co\u00f6rdinator kan inloggen!");
	}

	public IngelogdVerkeerdeRol(String s)
	{
		super(s);
	}

}
