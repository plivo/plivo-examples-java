package com.plivo.example.sms;

import com.plivo.example.Config;
import com.plivo.helper.PlivoRestConf;
import com.plivo.helper.exception.APIException;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.resource.Message;

public class CheckDeliveryStatus {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PlivoRestConf conf = new PlivoRestConf(Config.authId, Config.authToken);
		try {
			System.out.println("Trying to get message status");
			Message m = Message.get("146ddc50-7c09-11e3-944e-1231400195a3",
					conf);
			System.out.println("message state = " + m.getMessageState());

		} catch (PlivoException e) {
			System.out.println("PlivoException e : " + e.getMessage());
		} catch (APIException e) {
			System.out.println("APIException e : " + e.toString());
		}

	}

}
