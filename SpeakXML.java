package com.plivo.helper;

import com.plivo.sdk.xml.elements.Response;
import com.plivo.sdk.xml.elements.Speak;

public class HangupCall {
 	public static void main(String[] args) {
 		Response response = new Response();
 		Speak speak = new Speak();
 		
		// Set various attributes for speak
 		speak.setLanguage("en-US");
 		speak.setVoice("WOMAN");
 		speak.setText("Hi, Plivo calling.");
 		
		// Use dial in response
 		response.addSpeak(speak);

 		System.out.println(response.serializeToXML());
	}
}
