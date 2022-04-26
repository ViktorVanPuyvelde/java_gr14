package util;

import javax.persistence.AttributeConverter;

import org.eclipse.persistence.exceptions.JSONException;
import org.json.JSONArray;

public class JSONArrayConverter implements AttributeConverter<JSONArray, String>
{
	@Override
	public String convertToDatabaseColumn(JSONArray jsonData)
	{
		String json;
		try
		{
			json = jsonData.toString();
		} catch (NullPointerException ex)
		{
			// extend error handling here if you want
			json = "";
		}
		return json;
	}

	@Override
	public JSONArray convertToEntityAttribute(String jsonDataAsJson)
	{
		JSONArray jsonData;
		try
		{
			jsonData = new JSONArray(jsonDataAsJson);
		} catch (JSONException ex)
		{
			// extend error handling here if you want
			jsonData = null;
		}
		return jsonData;
	}
}
