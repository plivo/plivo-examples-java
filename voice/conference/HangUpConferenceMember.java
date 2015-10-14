package plivo.helper;

import java.util.LinkedHashMap;

import com.plivo.helper.api.client.RestAPI;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.api.response.response.GenericResponse;

public class HangUpConferenceMember {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		RestAPI restAPI = new RestAPI("<AUTH_ID>", "<AUTH_TOKEN>", "v1");
		
		LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
		
		params.put("conference_name", "1234");
		params.put("member_id", "5");
		
		try 
		{
			GenericResponse response = restAPI.hangupMember(params);
			System.out.println(response.message);
		}
		catch (PlivoException plivoException) {
			
			plivoException.printStackTrace();
		}

	}

}
