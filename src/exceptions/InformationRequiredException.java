package exceptions;

import java.util.Collections;
import java.util.Set;

import domein.RequiredElement;

public class InformationRequiredException extends Exception
{
	private static final long serialVersionUID = 1L;
	private Set<RequiredElement> informationRequired;

	public InformationRequiredException(String message, Set<RequiredElement> itemsRequired)
	{
		super(message);
		informationRequired = itemsRequired;
	}

	public Set<RequiredElement> getInformationRequired()
	{
		return Collections.unmodifiableSet(informationRequired);
	}
}