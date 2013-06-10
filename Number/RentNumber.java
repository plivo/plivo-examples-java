package plivo.helper;


import java.util.LinkedHashMap;

import com.plivo.helper.api.client.RestAPI;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.api.response.number.NumberResponse;

public class RentNumber {

  public static void main(String args[])
  {
		RestAPI restAPI = new RestAPI("<AUTH_ID>", "<AUTH_TOKEN>", "v1");

		NumberResponse numbers = new NumberResponse();
		LinkedHashMap<String ,String> params = new LinkedHashMap<String, String>();
		
		//Mandatory Parameter - group_id		
		params.put("group_id", "11542695876496");
		
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
			numbers = restAPI.rentNumbers(params);
			System.out.println(numbers.numberStatusList.get(0).number);
			System.out.println(numbers.numberStatusList.get(0).status);
			System.out.println(numbers.numberStatusList.get(1));
		}
		catch (PlivoException plivoException) 
		{
			System.out.println(plivoException.getMessage());;
		}
		
	}

}
