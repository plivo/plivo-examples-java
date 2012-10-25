package plivo.helper;

import java.util.LinkedHashMap;

import com.plivo.sdk.client.RestAPI;
import com.plivo.sdk.exception.PlivoException;
import com.plivo.sdk.response.number.NumberSearchFactory;

public class SearchAvailableNumbers {

	public static void main(String args[])
	{
		RestAPI restAPI = new RestAPI("<AUTH_ID>", "<AUTH_TOKEN>", "v1");

		NumberSearchFactory numbers = new NumberSearchFactory();
		LinkedHashMap<String ,String> params = new LinkedHashMap<String, String>();
		
		//Mandatory Parameter - country_iso		
		params.put("country_iso", "BE");
		
		//Optional Parameters - number_type, prefix, region, services, limit, offset
		/*
		params.put("number_type", "BE");
		params.put("prefix", "12345");
		params.put("region", "California");
		params.put("services", "voice");
		params.put("limit", "10");
		params.put("offset", "705");
		*/
		
		try 
		{
			numbers = restAPI.searchNumberGroups(params);
			System.out.println(numbers.numberList.get(0).groupID);
		}
		catch (PlivoException plivoException) 
		{
			System.out.println(plivoException.getMessage());;
		}
	}

}
