package plivo.helper;

import java.util.LinkedHashMap;

import com.plivo.sdk.client.RestAPI;
import com.plivo.sdk.exception.PlivoException;
import com.plivo.sdk.response.response.GenericResponse;


public class StopRecordingConference {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		RestAPI restAPI = new RestAPI("<AUTH_ID>", "<AUTH_TOKEN>", "v1");
		
		LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();

		params.put("conference_name", "1234");
		
		GenericResponse response = new GenericResponse();
		
		try
		{
			response = restAPI.stopRecordConference(params);
			System.out.println(response.apiID);
		} 
		catch (PlivoException plivoException) 
		{
			plivoException.printStackTrace();
		}
	}

}
