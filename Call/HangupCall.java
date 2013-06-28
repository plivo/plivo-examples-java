package plivo.helper;

import java.util.LinkedHashMap;

import com.plivo.helper.api.client.RestAPI;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.api.response.response.GenericResponse;

public class HangupCall {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		RestAPI restAPI = new RestAPI("<AUTH_ID>", "<AUTH_TOKEN>", "v1");
		
		LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();

		params.put("call_uuid", "6653422-91b6-4716-9fad-9463daaeeec2");
		
		GenericResponse response = new GenericResponse();
		
		try 
		{
			response = restAPI.hangupCall(params);
			System.out.println(response.apiID);
		}
		catch (PlivoException plivoException) 
		{
			plivoException.printStackTrace();
		}
	}

}
