package com.plivo.example.sms;

import java.util.List;

import com.plivo.example.Config;
import com.plivo.helper.PlivoRestConf;
import com.plivo.helper.exception.APIException;
import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.resource.Message;

public class Send {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PlivoRestConf conf = new PlivoRestConf(Config.authId, Config.authToken);
		try {
			List<String> uuidList = Message.send(Config.myPlivoNumber,
					Config.thirdNumber, "Test from java plivo api", conf);
			for (String uuid : uuidList) {
				System.out.println("uuid = " + uuid);
			}
			System.out.println("Message sent");

		} catch (PlivoException e) {
			System.out.println("PlivoException e : " + e.getMessage());
		} catch (APIException e) {
			System.out.println("APIException e : " + e.toString());
		}

	}
}
