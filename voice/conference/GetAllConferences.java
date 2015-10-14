package plivo.helper;

import com.plivo.helper.api.client.RestAPI;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.api.response.conference.LiveConferenceList;

public class GetAllConferences {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		RestAPI restAPI = new RestAPI("<AUTH_ID>", "<AUTH_TOKEN>", "v1");
		
		LiveConferenceList liveConferenceList = new LiveConferenceList();
		
		try 
		{
			liveConferenceList = restAPI.getLiveConferences();
			System.out.println(liveConferenceList.apiId);
			for (String conferenceName : liveConferenceList.conferences)
			{
				System.out.println("Conference name : " + conferenceName);
			}
		} 
		catch (PlivoException e) 
		{			
			e.printStackTrace();
		}

	}

}
