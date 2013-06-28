package plivo.helper;

import com.plivo.helper.api.client.RestAPI;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.api.response.response.GenericResponse;

public class HangupAllConferences {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		RestAPI restAPI = new RestAPI("<AUTH_ID>", "<AUTH_TOKEN>", "v1");
		
		GenericResponse genResponse = new GenericResponse();
		
		try 
		{
			genResponse = restAPI.hangupAllConferences();
			System.out.println(genResponse.apiId);
		} 
		catch (PlivoException plivoException) {
			plivoException.printStackTrace();
		}
	}

}
