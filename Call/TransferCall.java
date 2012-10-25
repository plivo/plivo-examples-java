package plivo.helper;

import java.util.LinkedHashMap;

import com.plivo.sdk.client.RestAPI;
import com.plivo.sdk.exception.PlivoException;
import com.plivo.sdk.response.response.GenericResponse;

public class TransferCall {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		RestAPI restAPI = new RestAPI("<AUTH_ID>", "<AUTH_TOKEN>", "v1");
		
		LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
		params.put("call_uuid", "6653422-91b6-4716-9fad-9463daaeeec2");
		
		/**
		 * Optional Parameters
		 * 
		 * legs : 'aleg', 'bleg' or 'both'
		 * aleg_url : URL to transfer for aleg, mandatory if legs is 'aleg' or 'both'.
		 * aleg_method : HTTP method GET or POST to use for aleg_url. POST is used by default.
		 * bleg_url : URL to transfer for bridged leg, mandatory if legs is 'bleg' or 'both'.
	     * bleg_method : HTTP method GET or POST to use for bleg_url. POST is used by default.
		 */
		
		params.put("bleg_url", "http://myxmlserver.com/xmldial");
		
		GenericResponse response = new GenericResponse();
		
		try 
		{
			response = restAPI.transferCall(params);
			System.out.println(response.apiID);
		}
		catch (PlivoException plivoException) 
		{
			plivoException.printStackTrace();
		}
	}

}
