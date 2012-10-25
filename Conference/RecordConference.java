package plivo.helper;

import java.util.LinkedHashMap;

import com.plivo.sdk.client.RestAPI;
import com.plivo.sdk.exception.PlivoException;
import com.plivo.sdk.response.response.Record;

public class RecordConference {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		RestAPI restAPI = new RestAPI("<AUTH_ID>", "<AUTH_TOKEN>", "v1");
		
		LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();

		params.put("conference_name", "1234");
		
		/**
		 * Optional parameters:
		 * 
		 * params.put("file_format","mp3");
		 */
		
		Record response = new Record();
		
		try
		{
			response = restAPI.recordConference(params);
			System.out.println(response.url);
		} 
		catch (PlivoException plivoException) 
		{
			plivoException.printStackTrace();
		}
	}

}
