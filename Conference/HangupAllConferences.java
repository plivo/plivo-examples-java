package plivo.helper;

import com.plivo.sdk.client.RestAPI;
import com.plivo.sdk.exception.PlivoException;
import com.plivo.sdk.response.response.GenericResponse;

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
			System.out.println(genResponse.apiID);
		} 
		catch (PlivoException plivoException) {
			plivoException.printStackTrace();
		}
	}

}
