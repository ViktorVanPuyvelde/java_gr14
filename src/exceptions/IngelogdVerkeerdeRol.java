package exceptions;

public class IngelogdVerkeerdeRol extends IllegalArgumentException {
	public IngelogdVerkeerdeRol(){
		super("Enkel de coördinator kan inloggen!");
	}

	public IngelogdVerkeerdeRol(String s) {
		super(s);
	}

	
}
