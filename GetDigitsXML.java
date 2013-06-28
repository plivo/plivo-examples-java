package com.plivo.helper;

import com.plivo.helper.xml.elements.PlivoResponse;
import com.plivo.helper.xml.elements.GetDigits;
import com.plivo.helper.xml.elements.Speak;

public class HangupCall {
 	public static void main(String[] args) {
 		PlivoResponse response = new PlivoResponse();
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
 		getdigits.addSpeak(speak);
 		
 		response.addGetDigits(getdigits);

 		System.out.println(response.serializeToXML());
	}
}
