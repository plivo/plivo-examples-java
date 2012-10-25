package plivo.helper;

import java.util.LinkedHashMap;

import com.plivo.sdk.client.RestAPI;
import com.plivo.sdk.exception.PlivoException;
import com.plivo.sdk.response.response.GenericResponse;

public class MakeMemberDeaf {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		RestAPI restAPI = new RestAPI("<AUTH_ID>", "<AUTH_TOKEN>", "v1");
		
		LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
		
		params.put("conference_name", "1234");
		params.put("member_id", "1");			/* single member_id or multiple or 'all' */
		
		GenericResponse response = new GenericResponse();
		
		try
		{
			response = restAPI.deafMember(params);
			System.out.println(response.apiID);
		} 
		catch (PlivoException plivoException) {

			plivoException.printStackTrace();
		}
	}

}
