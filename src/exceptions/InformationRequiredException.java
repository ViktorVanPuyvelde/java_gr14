package exceptions;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import domein.RequiredElement;

public class InformationRequiredException extends Exception
{
	private static final long serialVersionUID = 1L;
	private Set<RequiredElement> informationRequired;
	private Map<String, String> errorMap;

	public InformationRequiredException(String message, Set<RequiredElement> itemsRequired)
	{
		super(message);
		informationRequired = itemsRequired;
	}

	public InformationRequiredException(String message, Map<String, String> errorMap)
	{
		super(message);
		this.errorMap = errorMap;
	}

	public Set<RequiredElement> getInformationRequired()
	{
		return Collections.unmodifiableSet(informationRequired);
	}

	public Map<String, String> getErrorMap()
	{
		return Collections.unmodifiableMap(this.errorMap);
	}
}