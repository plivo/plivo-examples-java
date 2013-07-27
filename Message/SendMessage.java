package com.plivo.test;

import java.util.LinkedHashMap;

import com.plivo.helper.api.client.*;

import com.plivo.helper.api.response.message.MessageResponse;
import com.plivo.helper.exception.PlivoException;

public class SendMessage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String authId = "set your auth_id";
		String authToken = "set your auth_token";
		String src = "set your plivo number here";
		String dst = "set the destination number here";
		
		RestAPI api = new RestAPI(authId, authToken, "v1");
		
		LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("src", src);
		parameters.put("dst", dst);
		parameters.put("text", "Hello");
		parameters.put("url", "http://server/message/notification/");
		
		try {
			MessageResponse msgResponse = api.sendMessage(parameters);
			System.out.println(msgResponse.apiId);
			if (msgResponse.serverCode == 202) {
				System.out.println(msgResponse.messageUuids.get(0).toString());
			} else {
				System.out.println(msgResponse.error); 
			}
		} catch (PlivoException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	
}
