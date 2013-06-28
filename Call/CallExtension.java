package plivo.helper;

import java.util.LinkedHashMap;

import com.plivo.helper.api.client.RestAPI;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.api.response.call.Call;

public class CallExtension {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		RestAPI restAPI = new RestAPI("<AUTH_ID>", "<AUTH_TOKEN>", "v1");
		
		LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
		
		params.put("from", "xxxxxxxxxx");
		params.put("to", "xxxxxxxxxx");
		params.put("answer_url", "http://www.myxmlserver.com/Hangup.xml");
		params.put("send_digits", "1234");
		params.put("answer-method", "GET");
		
		Call response;
		try {

			response = restAPI.makeCall(params);
			System.out.println(response.apiId);
		} 
		catch (PlivoException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
