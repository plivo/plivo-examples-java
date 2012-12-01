package com.plivo.helper;

import com.plivo.sdk.xml.elements.GetDigits;
import com.plivo.sdk.xml.elements.Response;
import com.plivo.sdk.xml.elements.Speak;

public class HangupCall {
 	public static void main(String[] args) {
 		Response response = new Response();
 		Speak speak = new Speak();
 		GetDigits getdigits = new GetDigits();
 		
		// Set the various attributes for Speak
 		speak.setLanguage("en-US");
 		speak.setVoice("WOMAN");
 		speak.setText("Press one for English. Press two for Spanish");
 		
		// Set the various attributes for GetDigits
 		getdigits.setAction("http://myserverurl/input/");
 		getdigits.setMethod("GET");
		// Add speak to GetDigits
 		getdigits.setSpeak(speak);
 		
 		response.setGetDigits(getdigits);

 		System.out.println(response.serializeToXML());
	}
}
