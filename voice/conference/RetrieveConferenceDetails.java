package plivo.helper;

import java.util.LinkedHashMap;

import com.plivo.helper.api.client.RestAPI;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.api.response.conference.Conference;


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
