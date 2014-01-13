package com.plivo.example.voice;

import java.util.HashMap;

import com.plivo.example.Config;
import com.plivo.helper.PlivoRestConf;
import com.plivo.helper.exception.APIException;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.resource.Call;

/**
 * Call & Play MP3 file announcement
 */

public class CallWithIvr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PlivoRestConf conf = new PlivoRestConf(Config.authId, Config.authToken,
				Config.apiVersion);
		String answerUrl = "http://safe-stream-4972.herokuapp.com/incall/ivrforward";
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("answer_method", "GET");
		try {
			String requestUUID = Call.create(Config.myPlivoNumber,
					Config.firstNumber, answerUrl, params, conf);
			System.out.println("request uuid = " + requestUUID);
		} catch (PlivoException pe) {
			System.out.println("PlivoException : " + pe.getMessage());
		} catch (APIException ae) {
			System.out.println("APIException : " + ae.toString());
		}

	}
}
