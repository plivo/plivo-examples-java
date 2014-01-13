package com.plivo.example.voice;

import java.util.HashMap;

import com.plivo.example.Config;
import com.plivo.helper.PlivoRestConf;
import com.plivo.helper.exception.APIException;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.resource.Call;

public class Bridge2Record1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PlivoRestConf conf = new PlivoRestConf(Config.authId, Config.authToken,
				Config.apiVersion);
		String answerUrl = Config.plivoAppUrl + "/callbridgerecone";
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("answer_method", "GET");
		try {
			String requestUUID;
			System.out.println("call, bridge, and record");
			requestUUID = Call.create(Config.myPlivoNumber, Config.firstNumber,
					answerUrl, params, conf);
			System.out.println("request uuid = " + requestUUID);

			requestUUID = Call.create(Config.myPlivoNumber,
					Config.secondNumber, answerUrl, params, conf);
			System.out.println("request uuid = " + requestUUID);

		} catch (PlivoException pe) {
			System.out.println("PlivoException : " + pe.getMessage());
		} catch (APIException ae) {
			System.out.println("APIException : " + ae.toString());
		}

	}

}
