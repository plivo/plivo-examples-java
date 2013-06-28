package com.plivo.helper;

import com.plivo.helper.xml.elements.PlivoResponse;
import com.plivo.helper.xml.elements.Speak;

public class HangupCall {
 	public static void main(String[] args) {
 		PlivoResponse response = new PlivoResponse();
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
