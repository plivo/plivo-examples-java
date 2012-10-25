package plivo.helper;

import java.util.LinkedHashMap;

import com.plivo.sdk.client.RestAPI;
import com.plivo.sdk.exception.PlivoException;
import com.plivo.sdk.response.conference.Conference;


public class RetrieveConferenceDetails {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		RestAPI restAPI = new RestAPI("<AUTH_ID>", "<AUTH_TOKEN>", "v1");
		
		LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
		
		params.put("conference_name", "1234");
		
		try
		{
			Conference response = restAPI.getLiveConference(params);
			System.out.println("Conference Members :" + response.conferenceMemberCount);
		} 
		catch (PlivoException e) {
			e.printStackTrace();
		}
		

	}

}
