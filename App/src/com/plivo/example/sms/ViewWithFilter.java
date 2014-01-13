package com.plivo.example.sms;

import java.util.HashMap;

import com.plivo.example.Config;
import com.plivo.helper.PlivoRestConf;
import com.plivo.helper.exception.APIException;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.resource.Message;
import com.plivo.helper.resource.MessageList;

public class ViewWithFilter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PlivoRestConf conf = new PlivoRestConf(Config.authId, Config.authToken);
		try {
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("limit", 1);

			System.out.println("Contacting Plivo API server.....");
			MessageList ml = Message.getList(params, conf);
			for (Message m : ml.getList()) {
				System.out.println("Message details");
				System.out.println("\t type = " + m.getMessageType());
				System.out.println("\t uuid = " + m.getMessageUUID());
				System.out.println("\t time = " + m.getMessageTime());
				System.out.println("\t state = " + m.getMessageState());
				System.out.println("\t from = " + m.getFromNumber());
				System.out.println("\t to = " + m.getToNumber());
			}

		} catch (PlivoException e) {
			System.out.println("PlivoException e : " + e.getMessage());
		} catch (APIException e) {
			System.out.println("APIException e : " + e.toString());
		}
	}

}
