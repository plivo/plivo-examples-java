package plivo.helper;

import java.util.LinkedHashMap;
import com.plivo.sdk.client.RestAPI;
import com.plivo.sdk.exception.PlivoException;
import com.plivo.sdk.response.call.Call;

public class CallNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		RestAPI restAPI = new RestAPI("<AUTH_ID>", "<AUTH_TOKEN>", "v1");
		
		LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
		params.put("from", "xxxxxxxxxx");
		params.put("to", "xxxxxxxxxx");
		params.put("answer_url", "http://www.myxmlserver.com/Hangup.xml");

		Call response;
		try {

			response = restAPI.makeCall(params);
			System.out.println(response.apiID);
		} 
		catch (PlivoException e) {
			System.out.println(e.getMessage());
		}

	}

}
