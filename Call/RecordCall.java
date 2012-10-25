package plivo.helper;

import java.util.LinkedHashMap;

import com.plivo.sdk.client.RestAPI;
import com.plivo.sdk.exception.PlivoException;
import com.plivo.sdk.response.response.Record;

public class RecordCall {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		RestAPI restAPI = new RestAPI("<AUTH_ID>", "<AUTH_TOKEN>", "v1");
		
		LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
		
		params.put("call_uuid", "6653422-91b6-4716-9fad-9463daaeeec2");
		
		/**
		 * Optional Parameters : 
		 * params.put("time_limit":"75");
		 * params.put("file_format":"mp3");
		 * params.put("callback_url":"http://someurl");
		 * params.put("callback_method":"GET" or "POST");  - Defaults to POST
		 */
		
		Record response = new Record();
		
		try 
		{
			response = restAPI.record(params);
			System.out.println(response.url);
		} 
		catch (PlivoException plivoException)
		{
			plivoException.printStackTrace();
		}
	}

}
