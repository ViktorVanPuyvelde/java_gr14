package exceptions;

public class IngelogdVerkeerdeRol extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public IngelogdVerkeerdeRol(){
		super("Enkel de co�rdinator kan inloggen!");
	}

	public IngelogdVerkeerdeRol(String s)
	{
		super(s);
	}

}
