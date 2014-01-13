/**
 * Detect Machine vs Human (use AMD).
 * If machine play prerecorded message, else play default ivr to bridge call to a user
 */
package com.plivo.example.voice;

import java.util.HashMap;

import com.plivo.example.Config;
import com.plivo.helper.PlivoRestConf;
import com.plivo.helper.exception.APIException;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.resource.Call;

/**
 * Detect Machine vs Human (use AMD) - If machine play prerecorded message, else
 * play default ivr to bridge call to a user
 */

public class Amd {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PlivoRestConf conf = new PlivoRestConf(Config.authId, Config.authToken,
				Config.apiVersion);
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("answer_method", "GET");
		params.put("machine_detection", "true");
		try {
			String requestUUID = Call.create(Config.myPlivoNumber,
					Config.firstNumber,
					"http://safe-stream-4972.herokuapp.com/amd", params, conf);
			System.out.println("request uuid = " + requestUUID);
		} catch (PlivoException pe) {
			System.out.println("PlivoException : " + pe.getMessage());
		} catch (APIException ae) {
			System.out.println("APIException : " + ae.toString());
		}
	}
}
