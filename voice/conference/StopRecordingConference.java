package plivo.helper;

import java.util.LinkedHashMap;

import com.plivo.helper.api.client.RestAPI;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.api.response.response.GenericResponse;


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
			System.out.println(response.apiId);
		} 
		catch (PlivoException plivoException) 
		{
			plivoException.printStackTrace();
		}
	}

}
